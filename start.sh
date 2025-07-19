#!/bin/bash

# 🚀 Script de inicio para Railway - demoCardinalidad

echo "🚀 Iniciando demoCardinalidad en Railway..."

# Mostrar variables de entorno (sin passwords)
echo "📊 Variables de entorno:"
echo "PORT: $PORT"
echo "DB_HOST: $DB_HOST"
echo "DB_NAME: $DB_NAME"
echo "DB_USERNAME: $DB_USERNAME"
echo "JAVA_VERSION: $(java -version 2>&1 | head -n 1)"

# Verificar que el JAR existe
if [ ! -f "target/demoCardinalidad-0.0.1-SNAPSHOT.jar" ]; then
    echo "❌ Error: JAR file no encontrado"
    echo "📁 Contenido del directorio:"
    ls -la target/
    exit 1
fi

echo "✅ JAR file encontrado"

# Iniciar la aplicación
echo "🚀 Iniciando aplicación..."
java -jar target/demoCardinalidad-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod 