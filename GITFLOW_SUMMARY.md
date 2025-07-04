# Resumen del Flujo de Trabajo GitFlow

GitFlow es un modelo de ramificación de Git que ayuda a los equipos a gestionar proyectos de software de forma organizada. Se basa en dos ramas principales y varias ramas de soporte.

## Ramas Principales

Son ramas que viven para siempre en el proyecto.

-   `main` (o `master`): Esta rama contiene el código que está en **producción**. Es la versión más estable y limpia del proyecto. Nadie trabaja directamente en ella.
-   `develop`: Es la rama de **integración**. Contiene el código con las últimas funcionalidades que se han completado. Es la base para todo nuevo desarrollo.

## Ramas de Soporte

Son ramas temporales que se usan para tareas específicas y luego se eliminan.

-   **Ramas de Característica (`feature/*`)**
    -   **Propósito**: Desarrollar una nueva funcionalidad.
    -   **Se crea desde**: `develop`.
    -   **Se fusiona hacia**: `develop`.
    -   **Ejemplo de nombre**: `feature/login-con-google`

-   **Ramas de Lanzamiento (`release/*`)**
    -   **Propósito**: Preparar una nueva versión para producción. En esta rama solo se corrigen bugs y se prepara la documentación.
    -   **Se crea desde**: `develop`.
    -   **Se fusiona hacia**: `main` (para lanzar a producción) y `develop` (para incluir las últimas correcciones).
    -   **Ejemplo de nombre**: `release/v1.2.0`

-   **Ramas de Arreglo Rápido (`hotfix/*`)**
    -   **Propósito**: Corregir un error crítico que ha aparecido en producción.
    -   **Se crea desde**: `main`.
    -   **Se fusiona hacia**: `main` y `develop`.
    -   **Ejemplo de nombre**: `hotfix/error-pago-paypal`

---

## Flujo de Trabajo Típico

### 1. Desarrollar una Nueva Funcionalidad

1.  Un desarrollador se asegura de tener la última versión de `develop`:
    `git checkout develop`
    `git pull origin develop`
2.  Crea su propia rama de característica:
    `git checkout -b feature/nombre-de-la-funcionalidad`
3.  Trabaja y hace commits en su rama `feature`.
4.  Cuando termina, sube su rama al repositorio remoto:
    `git push origin feature/nombre-de-la-funcionalidad`
5.  Crea un **Pull Request** (PR) para fusionar su rama `feature` en `develop`.
6.  El equipo revisa el código y, una vez aprobado, se completa el PR.
7.  La rama `feature` se elimina.

### 2. Preparar un Lanzamiento (Release)

1.  Cuando `develop` tiene suficientes funcionalidades listas, se crea una rama de lanzamiento:
    `git checkout -b release/v1.1.0 develop`
2.  En esta rama, el equipo de QA realiza pruebas. Solo se hacen commits para corregir bugs.
3.  Una vez que la rama es estable, se fusiona con `main` y se le pone una etiqueta (tag):
    `git checkout main`
    `git merge release/v1.1.0`
    `git tag -a v1.1.0 -m "Lanzamiento versión 1.1.0"`
    `git push origin main --tags`
4.  También se fusiona con `develop` para que las correcciones hechas en la rama `release` pasen al flujo principal de desarrollo:
    `git checkout develop`
    `git merge release/v1.1.0`
5.  Finalmente, la rama `release` se elimina.

### 3. Corregir un Error Urgente (Hotfix)

1.  Se detecta un bug en producción (`main`).
2.  Se crea una rama `hotfix` desde `main`:
    `git checkout -b hotfix/bug-critico main`
3.  Se corrige el error y se hace commit.
4.  Se fusiona el arreglo en `main` y se crea una nueva etiqueta:
    `git checkout main`
    `git merge hotfix/bug-critico`
    `git tag -a v1.1.1 -m "Corrección de bug crítico"`
5.  También se fusiona en `develop` para que el arreglo no se pierda en el próximo lanzamiento:
    `git checkout develop`
    `git merge hotfix/bug-critico`
6.  La rama `hotfix` se elimina.
