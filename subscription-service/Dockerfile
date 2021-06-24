FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sub-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/sub-service.jar"]
