FROM amazoncorretto:21-alpine AS builder
WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=builder /app/target/serenityBackend-1.0-SNAPSHOT.jar serenity.jar


EXPOSE 8080


HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT:-8080}/actuator/health || exit 1


ENTRYPOINT ["java", "-jar", "serenity.jar"]