FROM ubuntu:18.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get install -y redis-tools && \
    apt-get clean;

WORKDIR /app

COPY build/libs/search-service.jar /app/search-service.jar

CMD ["java", "-jar", "search-service.jar"]