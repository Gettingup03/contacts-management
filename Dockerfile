FROM openjdk:8-alpine

WORKDIR /srv
COPY ./contacts-management.jar contacts-management.jar
CMD java -jar contacts-management.jar
