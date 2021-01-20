# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="Paathshala"

ARG JAR_FILE=build/libs/flight-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

# Add the application's jar to the container
COPY ${JAR_FILE} flight-service.jar

# Run the jar file 
ENTRYPOINT ["java", "-Dspring.profiles.active=prod","-jar","flight-service.jar"]