FROM amazoncorretto:21-alpine AS builder
WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=builder /app/target/serenityBackend-1.0-SNAPSHOT.jar serenity.jar

COPY src/main/resources/static ./static

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "serenity.jar"]