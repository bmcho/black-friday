FROM ubuntu:18.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

WORKDIR /app

COPY build/libs/delivery-service.jar /app/delivery-service.jar

CMD ["java", "-jar", "delivery-service.jar"]