package co.edu.corhuila.dlc.iam.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.edu.corhuila.dlc.iam.adapter.in.http.LoginErrors;
import co.edu.corhuila.dlc.iam.adapter.in.http.StaffUnlockController;
import co.edu.corhuila.dlc.iam.application.port.out.AccessTokenVerifier;
import co.edu.corhuila.dlc.iam.application.port.out.ActorLookup;
import co.edu.corhuila.dlc.iam.application.port.out.UnlockUnitOfWork;
import co.edu.corhuila.dlc.iam.application.usecase.CurrentActorService;
import co.edu.corhuila.dlc.iam.application.usecase.UnlockStaffService;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ForgedIdentityUnlockTest {
    private final UUID actorId = UUID.randomUUID();
    private final UUID sessionId = UUID.randomUUID();
    private final UUID targetId = UUID.randomUUID();
    private final AccessTokenVerifier verifier = mock(AccessTokenVerifier.class);
    private final ActorLookup lookup = mock(ActorLookup.class);
    private final UnlockUnitOfWork.Store store = mock(UnlockUnitOfWork.Store.class);
    private final Clock clock = Clock.fixed(Instant.parse("2026-10-28T20:00:00Z"), ZoneOffset.UTC);
    private final UnlockUnitOfWork unit = new UnlockUnitOfWork() {
        @Override public <T> T execute(Function<Store, T> operation) {
            return operation.apply(store);
        }
    };

    @Test
    void forgedAdminHeadersWithoutBearerReturn401WithoutUnlock() throws Exception {
        mvc().perform(post(path()).header("X-User-Id", actorId.toString())
                .header("X-User-Role", "ADMINISTRATOR"))
            .andExpect(status().isUnauthorized());
        verifyNoInteractions(verifier, lookup, store);
    }

    @Test
    void dentistBearerAndForgedAdminHeadersReturn403WithoutUnlock() throws Exception {
        when(verifier.verify(eq("dentist-token"), any(Instant.class)))
            .thenReturn(new AccessTokenVerifier.VerifiedAccessToken(actorId, sessionId));
        when(lookup.activeRoles(eq(actorId), eq(sessionId), any(Instant.class)))
            .thenReturn(List.of("DENTIST"));
        mvc().perform(post(path()).header("Authorization", "Bearer dentist-token")
                .header("X-User-Id", actorId.toString())
                .header("X-User-Role", "ADMINISTRATOR"))
            .andExpect(status().isForbidden());
        verify(store).isCurrentAdministrator(eq(actorId), eq(sessionId), any(Instant.class));
        verify(store, never()).findForUpdate(any(UUID.class));
        verify(store, never()).unlock(any(), any(), any(), any());
    }

    private String path() {
        return "/api/v1/auth/staff/" + targetId + "/unlocks";
    }

    private org.springframework.test.web.servlet.MockMvc mvc() {
        var actors = new CurrentActorService(verifier, lookup, clock);
        var unlocks = new UnlockStaffService(unit, clock);
        return MockMvcBuilders.standaloneSetup(new StaffUnlockController(actors, unlocks))
            .setControllerAdvice(new LoginErrors()).build();
    }
}
