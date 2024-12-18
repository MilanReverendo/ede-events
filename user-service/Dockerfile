# Use a specific OpenJDK 17 version
FROM openjdk:17-jdk-alpine

# Expose the port that the user-service will run on
EXPOSE 8081

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Optionally add a label for clarity
LABEL description="User Service Application" version="1.0"

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
