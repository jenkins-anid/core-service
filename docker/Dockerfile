FROM openjdk:17-jdk-slim
ENV APP_HOME = /opt/rspm/app
WORKDIR = $APP_HOME
USER root
COPY  /opt/rspm/app/core/rspm-core-service-0.0.1-SNAPSHOT.jar rspm-core-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","rspm-core-service.jar"]
