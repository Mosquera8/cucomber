FROM maven:3.9.7 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/pepino-0.0.1-SNAPSHOT.jar /app/pepino.jar
ENTRYPOINT ["java", "-jar", "/app/pepino.jar"]
