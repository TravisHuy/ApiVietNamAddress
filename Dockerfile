# Build stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml first
COPY pom.xml .
# Download dependencies
RUN mvn dependency:go-offline

# Copy source code and resources
COPY src ./src
# Ensure resources are copied
RUN mkdir -p target/classes
COPY src/main/resources/vietnamAddress.json target/classes/

# Build
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17-alpine
WORKDIR /app
# Copy the jar and ensure resources are included
COPY --from=build /app/target/*.jar app.jar
# Make sure the jar has read permissions
RUN chmod +x app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]