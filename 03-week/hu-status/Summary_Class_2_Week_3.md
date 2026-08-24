# Resumen de Clase 2 -- Semana 3

**Fecha:** 19 de agosto de 2026  
**Profesor:** Jesús Ariel González Bonilla  
**Tema:** Estructura y propósito de las carpetas del repositorio `docs`

Durante la **clase 2 de la semana 3**, realizada el **19 de agosto de 2026** con el profesor **Jesús Ariel González Bonilla**, se continuó con la revisión del repositorio `docs`. En esta clase se explicó con mayor detalle la **estructura de carpetas del repositorio y la función que cumple cada una dentro de la documentación del proyecto**.

El repositorio `docs` está organizado mediante carpetas numeradas desde **`00` hasta `15`**, además de la carpeta **`99-archive`** y la carpeta global **`assets`**. Esta numeración establece un **orden de lectura**, comenzando por los aspectos estratégicos y de gobierno del proyecto y avanzando progresivamente hacia los aspectos técnicos, operativos y de control.

## Estructura del repositorio

```text
docs/
├── README.md
├── CONTRIBUTING.md
├── CHANGELOG.md
├── LICENSE
│
├── 00-governance/
├── 01-context/
├── 02-domain/
├── 03-product/
├── 04-requirements/
├── 05-architecture/
├── 06-data/
├── 07-api/
├── 08-uml/
├── 09-microservices/
├── 10-devops/
├── 11-quality/
├── 12-ux-ui/
├── 13-operations/
├── 14-training/
├── 15-project-control/
├── 99-archive/
│
└── assets/
```

### `00-governance`

Contiene las **reglas y convenciones del proyecto**, incluyendo las prácticas ágiles, reglas de control de versiones, documentación, `Definition of Ready`, `Definition of Done` y las políticas de seguridad.

### `01-context`

Contiene el **contexto general del sistema**, su alcance, visión y el glosario utilizado para establecer un lenguaje común dentro del proyecto.

### `02-domain`

Contiene el **modelo de dominio**, incluyendo las entidades, reglas de negocio, mapa del dominio y eventos de dominio.

### `03-product`

Está relacionada con la **visión del producto**, el problema que se busca solucionar, el discovery, el backlog del producto y el roadmap.

### `04-requirements`

Contiene los **requisitos funcionales y no funcionales**, las historias de usuario y la matriz de trazabilidad que permite relacionar requisitos, historias de usuario y pruebas.

### `05-architecture`

Contiene la documentación relacionada con la **arquitectura del sistema**, incluyendo la visión general, despliegue, patrones, aspectos transversales y modelo de amenazas.

Dentro de esta carpeta se encuentra `decisions/records`, donde se almacenan los **Architecture Decision Records (ADRs)**, utilizados para registrar las decisiones arquitectónicas importantes y sus respectivas justificaciones.

### `06-data`

Contiene la documentación relacionada con los **datos**, incluyendo modelos de datos, diccionario de datos, convenciones de modelado, normalización y estrategia de migraciones.

### `07-api`

Contiene la documentación de las **APIs**, sus lineamientos y autenticación. También incluye `contracts/openapi`, donde se encuentran los contratos utilizados para documentar las APIs.

### `08-uml`

Contiene los **diagramas UML del sistema**. La carpeta `diagrams/source` almacena las fuentes editables de los diagramas, mientras que `diagrams/exports` contiene las imágenes exportadas que pueden ser utilizadas dentro de la documentación.

### `09-microservices`

Contiene la documentación específica de los **microservicios**, incluyendo el catálogo de servicios, dependencias, propiedad de datos, eventos, patrones de comunicación y reglas de frontera.

También contiene plantillas en `_template` y las carpetas `services`, donde cada servicio tiene su propia documentación y sus componentes, como APIs, workers y workflows.

### `10-devops`

Contiene la documentación relacionada con **DevOps**, incluyendo CI/CD, ambientes y configuración del entorno local, además de procesos de deployment, release y rollback.

### `11-quality`

Contiene la información relacionada con **calidad y pruebas**, incluyendo la estrategia de testing, revisión de código y evidencias de pruebas.

### `12-ux-ui`

Contiene la documentación de **UX/UI**, como el design system, wireframes, navegación y mockups.

La carpeta `mockups` permite organizar los flujos, mockups por dominio, el shell común, los recursos y los screenshots para desktop y mobile.

### `13-operations`

Está enfocada en la **operación del sistema**, incluyendo observabilidad, backups, recuperación, gestión de incidentes y runbooks.

También puede contener documentación relacionada con `SLA`, `SLO` y `SLI`.

### `14-training`

Contiene la documentación de **formación y capacitación**, incluyendo manuales para usuarios y administradores y documentación de onboarding técnico.

### `15-project-control`

Contiene información relacionada con el **control del proyecto**, incluyendo riesgos, dependencias, preguntas abiertas, deuda técnica y registros relacionados con los sprints.

### `99-archive`

Esta carpeta contiene la **documentación histórica** que ya no está vigente, pero que se conserva para mantener la trazabilidad del proyecto.

Dentro de ella se encuentran documentos `deprecated` y decisiones antiguas (`old-decisions`).

La idea principal es que **la documentación no se elimina**, sino que cuando deja de ser válida puede pasar al archivo histórico.

### `assets`

Contiene los **recursos globales del repositorio**, como diagramas, imágenes y logos que pueden ser utilizados por diferentes documentos.

## Convenciones explicadas

Durante la clase también se revisaron algunas convenciones importantes de la estructura:

- El prefijo numérico **`00`–`15`** establece el orden de lectura de la documentación.
- Cada carpeta debe contar con su propio **`README.md`**, donde se explica qué información contiene y cómo debe utilizarse.
- Los archivos **`_template-*.md`** funcionan como plantillas para crear nuevos documentos manteniendo un formato uniforme.
- Los servicios utilizan una numeración como `service-01`, `service-02`, etc., y cada servicio documenta sus componentes.
- La carpeta **`99-archive`** permite conservar documentos obsoletos y decisiones anteriores para mantener la trazabilidad histórica.

## Conclusión

En general, la **clase 2 de la semana 3** permitió comprender que el repositorio `docs` no es simplemente una carpeta donde se almacenan archivos, sino una estructura organizada que funciona como una **fuente única de información para el proyecto**.

La organización numérica permite seguir un orden lógico desde el gobierno y contexto del proyecto hasta la arquitectura, datos, APIs, microservicios, operaciones y control. Además, el uso de `README.md`, plantillas, recursos compartidos y una carpeta de archivo permite mantener una documentación **ordenada, consistente y trazable durante todo el ciclo de vida del proyecto**.
