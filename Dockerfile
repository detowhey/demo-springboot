FROM maven:3.8.6-eclipse-temurin-17-alpine as build
MAINTAINER Henrique de Freitas Almeida
ENV APP_NAME demo-springboot
COPY . /app
WORKDIR /app
#RUN mvn clean install
#RUN mv -f target/*.jar ${APP_NAME}.jar
#ENTRYPOINT ["java", "-jar", "${APP_NAME}.jar"]
ENTRYPOINT ["java", "-jar"]
EXPOSE 8080
