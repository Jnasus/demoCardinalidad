# Imagen base de Java 17
FROM openjdk:17-jdk-slim

# Copiamos el archivo .jar compilado al contenedor
COPY target/demoCardinalidad-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "/app.jar"]
