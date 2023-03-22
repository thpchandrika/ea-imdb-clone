FROM openjdk:17
LABEL MAINTAINER="KRISHNA SHRESTHA"
LABEL APPLICATION="TV SERIES Service"
COPY target/service-discovery.jar /home/dockerImage/service-discovery.jar
ENTRYPOINT ["java","-jar","/home/dockerImage/service-discovery.jar"]