FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sub-service.jar
ENTRYPOINT ["java","-jar","/sub-service.jar"]
EXPOSE 443