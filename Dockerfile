# Stage 1: Build the application
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/books-api-1.0.jar books-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "books-api.jar"]
