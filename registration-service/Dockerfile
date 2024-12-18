FROM openjdk:17-jdk-slim
WORKDIR /app
ADD target/registration-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
