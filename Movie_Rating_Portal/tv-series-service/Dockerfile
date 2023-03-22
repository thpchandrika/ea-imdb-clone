FROM openjdk:17
LABEL MAINTAINER="KRISHNA SHRESTHA"
LABEL APPLICATION="TV SERIES Service"
COPY target/tv-series-service.jar /home/dockerImage/tv-series-service.jar
ENTRYPOINT ["java","-jar","/home/dockerImage/tv-series-service.jar"]