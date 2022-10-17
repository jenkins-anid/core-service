FROM openjdk:17-jdk-slim
ENV APP_HOME = /opt/rspm/app/core
WORKDIR = $APP_HOME
COPY target/*.jar rspm-core-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","rspm-core-service.jar"]
