FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar gpsUtil-1.0.0.jar
ENTRYPOINT ["java","-jar","/gpsUtil-1.0.0.jar"]