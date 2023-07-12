FROM openjdk:20-slim

EXPOSE 8080

RUN apt-get update
RUN apt-get install -y maven
RUN apt-get install -y wget
#
RUN wget https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-8.8.1-amd64.deb
RUN apt-get install -y 	 ./filebeat-8.8.1-amd64.deb

COPY filebeat.yml /etc/filebeat/filebeat.yml

COPY ./demo /app
RUN cd app && mvn clean package -DskipTests

ENTRYPOINT service filebeat start && java -jar /app/target/poetry-backend.jar