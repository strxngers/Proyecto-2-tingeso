FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} calidad-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/calidad-service-0.0.1-SNAPSHOT.jar"]