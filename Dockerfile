FROM maven:3.8.3-eclipse-temurin-17 as build
MAINTAINER Henrique de Freitas Almeida
WORKDIR /app
RUN mvn package
COPY target/demo-springboot-0.0.1-SNAPSHOT .
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo-springboot-0.0.1-SNAPSHOT.jar"]
