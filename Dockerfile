FROM openjdk:17-jdk-alpine
MAINTAINER michal.mech@gmail.com
COPY target/discount-service-0.1.jar discount-service-0.1.jar
ENTRYPOINT ["java","-jar","/discount-service-0.1.jar"]
EXPOSE 8080