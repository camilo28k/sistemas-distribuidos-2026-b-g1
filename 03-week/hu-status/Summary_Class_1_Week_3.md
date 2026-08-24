# Resumen de Clase 1 -- Semana 3

**Fecha:** 18 de agosto de 2026  
**Profesor:** Jesús Ariel González Bonilla  
**Tema:** Estructura y organización del repositorio `docs`

Durante la **clase 1 de la semana 3**, realizada el **18 de agosto de 2026** con el profesor **Jesús Ariel González Bonilla**, se revisó la **estructura y organización del repositorio `docs`** correspondiente al proyecto de Sistemas Distribuidos.

Una de las principales indicaciones del profesor fue que debíamos **revisar y familiarizarnos con la estructura del repositorio `docs`**, comprendiendo cómo está organizada la documentación y qué función cumple cada una de sus secciones.

## Estructura del repositorio

```text
docs/
├── README.md
├── CONTRIBUTING.md
├── CHANGELOG.md
├── LICENSE
├── <render-config>
│
├── 00-governance/
├── 01-context/
├── 02-domain/
├── 03-product/
├── 04-requirements/
├── 05-architecture/
│   └── decisions/
│       └── records/
├── 06-data/
├── 07-api/
│   └── contracts/
│       └── openapi/
├── 08-uml/
│   └── diagrams/
│       ├── source/
│       └── exports/
├── 09-microservices/
│   ├── _template/
│   │   ├── service/
│   │   └── component/
│   └── services/
│       ├── service-01/
│       │   └── components/
│       │       └── <api>/
│       ├── service-02/
│       │   └── components/
│       │       ├── <api>/
│       │       ├── <worker>/
│       │       └── <workflow>/
│       └── service-NN/
│           └── components/
├── 10-devops/
├── 11-quality/
├── 12-ux-ui/
│   └── mockups/
│       ├── flows/
│       └── app/
│           ├── <dominio-1>/
│           ├── <dominio-2>/
│           ├── shell/
│           ├── assets/
│           └── screenshots/
│               ├── desktop/
│               └── mobile/
├── 13-operations/
├── 14-training/
├── 15-project-control/
├── 99-archive/
│   ├── deprecated/
│   └── old-decisions/
│
└── assets/
    ├── diagrams/
    ├── images/
    └── logos/
```

Durante la clase también se explicó el manejo de **ramas en el repositorio de documentación**. El profesor indicó que el repositorio `docs` **no debe manejar diferentes ramas para mantener versiones de la documentación**, ya que no tendría sentido tener una versión de la documentación en producción mientras otra versión se encuentra en proceso de actualización en una rama diferente.

Por ejemplo, si se encuentra un error en la documentación que está actualmente disponible en producción, no sería conveniente realizar la corrección únicamente en otra rama mientras los desarrolladores continúan consultando la versión anterior. Por esta razón, las actualizaciones y correcciones de la documentación se realizan **directamente en `main`**, permitiendo que todo el equipo trabaje siempre con la versión actualizada.

El objetivo de esta estrategia es mantener una **única versión actual de la documentación**, evitando inconsistencias entre lo que se encuentra publicado y la información que utilizan los desarrolladores durante el desarrollo del proyecto.

También se nos indicó que debíamos **continuar revisando la estructura del repositorio `docs`**, con el propósito de comprender su organización y familiarizarnos con la ubicación de los diferentes documentos que hacen parte del proyecto.

En general, la **clase 1 de la semana 3** estuvo enfocada en comprender la importancia de mantener una documentación organizada y actualizada. Se reforzó la idea de que los cambios en `docs` deben realizarse directamente sobre `main`, evitando mantener versiones diferentes de la documentación en ramas separadas, para garantizar que los desarrolladores siempre tengan acceso a la información más reciente del proyecto.
