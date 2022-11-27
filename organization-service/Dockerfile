#base image
FROM openjdk:11

WORKDIR /app

ADD target/organization-service-0.0.1-SNAPSHOT.jar organization-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080
#download and install dependency

#commands to run container start
ENTRYPOINT ["java","-jar","organization-service-0.0.1-SNAPSHOT.jar"]