# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Expose port 8083
EXPOSE 8083

# Define build-time arguments for Google OAuth
ARG GOOGLE_CLIENTID
ARG GOOGLE_CLIENTSECRET

# Set environment variables that Spring Boot will use
ENV GOOGLE_CLIENTID=$GOOGLE_CLIENTID
ENV GOOGLE_CLIENTSECRET=$GOOGLE_CLIENTSECRET

# Add the Spring Boot JAR file to the image
ADD target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
