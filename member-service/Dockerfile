FROM ubuntu:18.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

WORKDIR /app

COPY build/libs/member-service.jar /app/member-service.jar

CMD ["java", "-jar", "member-service.jar"]
