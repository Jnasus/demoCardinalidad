<<<<<<< HEAD
# Multi-stage build para optimizar el tamaño de la imagen
FROM maven:3.9.6-openjdk-17 AS build

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de dependencias primero (para aprovechar la caché de Docker)
COPY pom.xml .
COPY src ./src

# Instalar dependencias y construir la aplicación
RUN mvn clean package -DskipTests

# Segunda etapa: imagen de ejecución
FROM openjdk:17-jre-slim

# Instalar dependencias del sistema
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Crear usuario no-root para seguridad
RUN groupadd -r spring && useradd -r -g spring spring

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Cambiar propietario de los archivos
RUN chown -R spring:spring /app

# Cambiar al usuario no-root
USER spring

# Exponer puerto
EXPOSE 8081

# Variables de entorno para la aplicación
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando de inicio
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
=======

>>>>>>> b5d0460acdc6eab0a8432c776c7c8e0432f260cd
