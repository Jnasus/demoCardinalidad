#!/bin/bash

# Script de despliegue automatizado
# Uso: ./deploy.sh [staging|production]

set -e

ENVIRONMENT=${1:-staging}
DOCKER_COMPOSE_FILE="docker-compose.yml"

echo "🚀 Iniciando despliegue en ambiente: $ENVIRONMENT"

# Función para verificar si Docker está ejecutándose
check_docker() {
    if ! docker info > /dev/null 2>&1; then
        echo "❌ Docker no está ejecutándose"
        exit 1
    fi
    echo "✅ Docker está ejecutándose"
}

# Función para hacer backup de la base de datos
backup_database() {
    if [ "$ENVIRONMENT" = "production" ]; then
        echo "💾 Creando backup de la base de datos..."
        docker exec demo-cardinalidad-mysql mysqldump -u root -proot dbCardinalidad > backup_$(date +%Y%m%d_%H%M%S).sql
        echo "✅ Backup creado exitosamente"
    fi
}

# Función para verificar la salud de la aplicación
health_check() {
    echo "🏥 Verificando salud de la aplicación..."
    local max_attempts=30
    local attempt=1
    
    while [ $attempt -le $max_attempts ]; do
        if curl -f http://localhost:8081/actuator/health > /dev/null 2>&1; then
            echo "✅ Aplicación está saludable"
            return 0
        fi
        
        echo "⏳ Intento $attempt/$max_attempts - Esperando que la aplicación esté lista..."
        sleep 10
        attempt=$((attempt + 1))
    done
    
    echo "❌ La aplicación no está respondiendo después de $max_attempts intentos"
    return 1
}

# Función para ejecutar pruebas de integración
run_integration_tests() {
    echo "🧪 Ejecutando pruebas de integración..."
    
    # Verificar endpoints principales
    curl -f http://localhost:8081/api/v1/categoria > /dev/null 2>&1 || { echo "❌ Error en endpoint de categorías"; exit 1; }
    curl -f http://localhost:8081/api/v1/producto > /dev/null 2>&1 || { echo "❌ Error en endpoint de productos"; exit 1; }
    
    echo "✅ Pruebas de integración exitosas"
}

# Función para limpiar recursos no utilizados
cleanup() {
    echo "🧹 Limpiando recursos no utilizados..."
    docker system prune -f
    echo "✅ Limpieza completada"
}

# Función principal de despliegue
deploy() {
    echo "📦 Iniciando despliegue..."
    
    # Detener contenedores existentes
    echo "🛑 Deteniendo contenedores existentes..."
    docker-compose -f $DOCKER_COMPOSE_FILE down
    
    # Hacer pull de las últimas imágenes
    echo "⬇️ Descargando últimas imágenes..."
    docker-compose -f $DOCKER_COMPOSE_FILE pull
    
    # Iniciar contenedores
    echo "🚀 Iniciando contenedores..."
    docker-compose -f $DOCKER_COMPOSE_FILE up -d
    
    # Verificar salud de la aplicación
    health_check
    
    # Ejecutar pruebas de integración
    run_integration_tests
    
    echo "✅ Despliegue completado exitosamente!"
}

# Función para rollback
rollback() {
    echo "🔄 Iniciando rollback..."
    
    # Detener contenedores
    docker-compose -f $DOCKER_COMPOSE_FILE down
    
    # Restaurar versión anterior (implementar según tu estrategia)
    echo "📦 Restaurando versión anterior..."
    
    # Reiniciar con versión anterior
    docker-compose -f $DOCKER_COMPOSE_FILE up -d
    
    echo "✅ Rollback completado"
}

# Función para mostrar logs
show_logs() {
    echo "📋 Mostrando logs de la aplicación..."
    docker-compose -f $DOCKER_COMPOSE_FILE logs -f app
}

# Función para mostrar estado
show_status() {
    echo "📊 Estado de los contenedores:"
    docker-compose -f $DOCKER_COMPOSE_FILE ps
}

# Manejo de argumentos
case "$1" in
    "staging"|"production")
        check_docker
        backup_database
        deploy
        cleanup
        ;;
    "rollback")
        rollback
        ;;
    "logs")
        show_logs
        ;;
    "status")
        show_status
        ;;
    *)
        echo "Uso: $0 {staging|production|rollback|logs|status}"
        echo "  staging    - Desplegar en ambiente de staging"
        echo "  production - Desplegar en ambiente de producción"
        echo "  rollback   - Hacer rollback a versión anterior"
        echo "  logs       - Mostrar logs de la aplicación"
        echo "  status     - Mostrar estado de los contenedores"
        exit 1
        ;;
esac 