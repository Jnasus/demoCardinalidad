# 🚀 Despliegue en Railway - demoCardinalidad

## 📋 Prerrequisitos

- ✅ Cuenta en [Railway](https://railway.app)
- ✅ Proyecto conectado a GitHub
- ✅ Base de datos MySQL configurada en Railway

## 🔧 Configuración de Variables de Entorno

### 📝 Crear archivo `.env`

Crea un archivo `.env` en la raíz del proyecto con el siguiente contenido:

```env
# 🚀 Variables de Entorno para Railway - demoCardinalidad

# 📊 Configuración de la Base de Datos MySQL
DB_HOST=tu-host-mysql-railway
DB_PORT=3306
DB_NAME=tu-nombre-db
DB_USERNAME=tu-usuario
DB_PASSWORD=tu-password

# 🔧 Configuración del Servidor
SERVER_PORT=8081

# 🗄️ Configuración JPA
JPA_DDL_AUTO=create-drop
JPA_SHOW_SQL=true

# 🌐 Configuración de la Aplicación
SPRING_APPLICATION_NAME=demoCardinalidad

# 📝 Configuración de Swagger/OpenAPI
SWAGGER_API_DOCS_PATH=/api-docs
SWAGGER_UI_PATH=/swagger-ui.html

# 🔄 Configuración de Inicialización de Base de Datos
SQL_INIT_MODE=always
SQL_INIT_CONTINUE_ON_ERROR=true
SQL_INIT_ENCODING=UTF-8
```

## 🚀 Pasos para el Despliegue

### 1️⃣ Configurar Base de Datos MySQL en Railway

1. Ve a tu proyecto en Railway
2. Haz clic en "New Service" → "Database" → "MySQL"
3. Anota las credenciales de conexión

### 2️⃣ Configurar Variables de Entorno en Railway

1. Ve a tu servicio de aplicación en Railway
2. Ve a la pestaña "Variables"
3. Agrega las siguientes variables:

| Variable | Valor | Descripción |
|----------|-------|-------------|
| `DB_HOST` | Tu host MySQL de Railway | Host de la base de datos |
| `DB_PORT` | 3306 | Puerto de MySQL |
| `DB_NAME` | Tu nombre de DB | Nombre de la base de datos |
| `DB_USERNAME` | Tu usuario MySQL | Usuario de la base de datos |
| `DB_PASSWORD` | Tu password MySQL | Contraseña de la base de datos |
| `SERVER_PORT` | 8081 | Puerto del servidor |
| `JPA_DDL_AUTO` | create-drop | Estrategia de creación de tablas |
| `JPA_SHOW_SQL` | true | Mostrar SQL en logs |
| `SPRING_APPLICATION_NAME` | demoCardinalidad | Nombre de la aplicación |

### 3️⃣ Desplegar la Aplicación

1. Conecta tu repositorio de GitHub a Railway
2. Railway detectará automáticamente que es un proyecto Java/Maven
3. El archivo `railway.json` configurará el build y deploy
4. Railway construirá y desplegará automáticamente

## 🔍 Verificación del Despliegue

### ✅ Healthcheck
- URL: `https://tu-app.railway.app/actuator/health`
- Debe retornar: `{"status":"UP"}`

### 📚 Swagger UI
- URL: `https://tu-app.railway.app/swagger-ui.html`
- Documentación de la API

### 🔗 Endpoints Disponibles

| Endpoint | Descripción |
|----------|-------------|
| `/api/categorias` | CRUD de categorías |
| `/api/clientes` | CRUD de clientes |
| `/api/productos` | CRUD de productos |
| `/swagger-ui.html` | Documentación Swagger |
| `/actuator/health` | Estado de la aplicación |

## 🛠️ Troubleshooting

### ❌ Error de Conexión a Base de Datos
- Verifica que las variables de entorno estén correctamente configuradas
- Asegúrate de que la base de datos MySQL esté activa en Railway

### ❌ Error de Puerto
- Railway asignará automáticamente el puerto, pero puedes usar `PORT` como variable de entorno
- Modifica `server.port=${PORT:8081}` en `application.properties`

### ❌ Error de Build
- Verifica que el proyecto compile localmente: `mvn clean package`
- Revisa los logs de build en Railway

## 📊 Monitoreo

- **Logs**: Ve a la pestaña "Deployments" en Railway
- **Métricas**: Railway proporciona métricas básicas de CPU y memoria
- **Healthcheck**: Automático cada 30 segundos

## 🔄 Actualizaciones

Para actualizar la aplicación:
1. Haz push a tu rama principal en GitHub
2. Railway detectará automáticamente los cambios
3. Construirá y desplegará la nueva versión

---

## 🎯 Notas Importantes

- ⚠️ **Base de Datos**: Railway puede reiniciar la base de datos, por eso usamos `create-drop`
- 🔒 **Seguridad**: Nunca subas el archivo `.env` a GitHub
- 📝 **Logs**: Los logs de la aplicación estarán disponibles en Railway
- 🚀 **Escalabilidad**: Railway permite escalar automáticamente según la demanda 