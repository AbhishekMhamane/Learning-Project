#base image
FROM openjdk:11

WORKDIR /app

ADD target/employee-service-0.0.1-SNAPSHOT.jar employee-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081
#download and install dependency

#commands to run container start
ENTRYPOINT ["java","-jar","employee-service-0.0.1-SNAPSHOT.jar"]