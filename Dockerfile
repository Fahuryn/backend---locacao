FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080
# comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]