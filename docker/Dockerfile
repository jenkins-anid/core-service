FROM openjdk:17-jdk-slim
ENV APP_HOME = /opt/rspm/app
WORKDIR = $APP_HOME
#USER root
COPY core-service/target/*.jar rspm-core-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","rspm-core-service.jar"]
