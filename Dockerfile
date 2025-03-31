FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/training-management-system.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]