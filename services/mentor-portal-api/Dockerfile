# Build step
FROM maven:3.6.3-openjdk-14-slim AS build
WORKDIR /app

# Copy the pom.xml separately so we can take care of Docker caching
COPY pom.xml .
RUN mvn clean dependency:go-offline -B

# Add source code and create .jar
COPY src ./src
RUN mvn package

# Package image
FROM openjdk:14-jdk-alpine

EXPOSE 8090

COPY --from=build /app/target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]