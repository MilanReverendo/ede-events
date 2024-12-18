# Use a specific OpenJDK 17 version
FROM openjdk:17-jdk-alpine

# Expose the port your app will run on
EXPOSE 8081

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Optionally add a label
LABEL description="Event Service Application" version="1.0"

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
