# Build stage
FROM gradle:latest AS build
WORKDIR /home/gradle/src

# Asegurar que Java 17 está disponible
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Copiar código fuente
COPY --chown=gradle:gradle . .

# Configurar Gradle para usar Java 17
RUN gradle clean
RUN gradle bootJar

# Package stage
FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "/app.jar"]
