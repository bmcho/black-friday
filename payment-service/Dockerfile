FROM ubuntu:18.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

WORKDIR /app

COPY build/libs/payment-service.jar /app/payment-service.jar

CMD ["java", "-jar", "payment-service.jar"]