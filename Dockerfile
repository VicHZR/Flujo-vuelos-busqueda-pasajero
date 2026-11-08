# Etapa 1: Compilación con Maven y Temurin Java 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src src
RUN mvn -q -DskipTests package

# Etapa 2: Imagen final ligera solo con JRE
FROM eclipse-temurin:21-jre
RUN useradd --system --uid 10001 app
WORKDIR /app

COPY --from=build /workspace/target/*.jar app.jar
USER app

# El puerto se documenta aquí (en el docker-compose se mapea hacia afuera)
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]