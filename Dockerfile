# Etapa 1: Build - Compilar la aplicación con Maven
# Usamos una imagen oficial de Maven que incluye JDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descargamos todas las dependencias de Maven. Se cachean en una capa separada.
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente de la aplicación
COPY src ./src

# Compilamos la aplicación y ejecutamos las pruebas, generando el .jar
RUN mvn package -DskipTests

# Etapa 2: Run - Crear la imagen final de ejecución
# Usamos una imagen oficial de OpenJDK 17 más ligera, solo para ejecutar
FROM openjdk:17-jdk-slim

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo .jar generado en la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080, que es el que usa Spring Boot por defecto
EXPOSE 8080

# El comando que se ejecutará cuando el contenedor inicie
ENTRYPOINT ["java", "-jar", "app.jar"]
