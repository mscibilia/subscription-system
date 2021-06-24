FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} public-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/public-service.jar"]