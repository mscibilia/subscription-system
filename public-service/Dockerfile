FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} public-service.jar
ENTRYPOINT ["java","-jar","/public-service.jar"]
EXPOSE 443