FROM maven:3.8.1-openjdk-8 AS build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package

FROM openjdk:8
COPY --from=build /tmp/target/*.jar email-service.jar
ENTRYPOINT ["java","-jar","/email-service.jar"]