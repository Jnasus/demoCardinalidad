# Proyecto Demo Cardinalidad

Este es un proyecto de demostración para gestionar productos y categorías, construido con Spring Boot.

## Flujo de Trabajo con GitFlow

Este proyecto utiliza el modelo de ramificación **GitFlow** para organizar el desarrollo. Las ramas principales son:

-   `main`: Contiene el código estable que está en producción.
-   `develop`: Es la rama de integración donde se unen todas las nuevas funcionalidades.

Las ramas de soporte que utilizamos son:
-   `feature/*`: Para desarrollar nuevas funcionalidades (ej. `feature/cliente`).
-   `release/*`: Para preparar un nuevo lanzamiento a producción.
-   `hotfix/*`: Para solucionar errores críticos en producción.

Todo el trabajo nuevo se realiza en una rama `feature` que nace de `develop` y vuelve a ella a través de un **Pull Request** para ser revisado por el equipo.

## Funcionalidades

### Módulo de Clientes (En Desarrollo)

Actualmente se está trabajando en la implementación del CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad **Cliente**.

El trabajo se está realizando en la rama `feature/cliente` y comprende la creación de los siguientes componentes:
-   **Modelo**: `Cliente.java`
-   **Repositorio**: `ClienteRepository.java`
-   **Servicio**: `ClienteService.java`
-   **Controlador**: `ClienteController.java`
-   **DTO**: `ClienteDTO.java`

Los nuevos endpoints serán documentados y accesibles a través de Swagger una vez que la funcionalidad sea integrada en la rama `develop`.
