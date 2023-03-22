FROM openjdk:17
LABEL MAINTAINER="KRISHNA SHRESTHA"
LABEL APPLICATION=""
COPY target/api-cloud-gateway.jar /home/dockerImage/api-cloud-gateway.jar
ENTRYPOINT ["java","-jar","/home/dockerImage/api-cloud-gateway.jar"]