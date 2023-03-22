FROM openjdk:17
LABEL MAINTAINER="KRISHNA SHRESTHA"
LABEL APPLICATION=""
COPY target/config-server.jar /home/dockerImage/config-server.jar
ENTRYPOINT ["java","-jar","/home/dockerImage/config-server.jar"]