FROM eclipse-temurin:21

COPY backend-api-1.0.0.jar backend-api.jar

CMD ["--", "java", "-Dspring.profiles.active=postgres", "-jar", "backend-api.jar"]

EXPOSE 8080