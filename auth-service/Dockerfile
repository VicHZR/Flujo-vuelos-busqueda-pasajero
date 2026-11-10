# Etapa 1: Compilación con Maven y Temurin Java 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copiamos todo el repositorio raíz para que Maven reconozca el pom.xml padre y los submódulos
COPY . .

# Recibimos el nombre del servicio dinámicamente desde el docker-compose
ARG SERVICE_NAME

# Compilamos únicamente el submódulo actual y sus dependencias hermanas
RUN mvn -q -pl ${SERVICE_NAME} -am clean package -DskipTests

# Etapa 2: Imagen final ligera solo con JRE
FROM eclipse-temurin:21-jre
RUN useradd --system --uid 10001 app
WORKDIR /app

# Copiamos el jar generado del servicio correspondiente
ARG SERVICE_NAME
COPY --from=build /workspace/${SERVICE_NAME}/target/*.jar app.jar
USER app

ENTRYPOINT ["java", "-jar", "app.jar"]