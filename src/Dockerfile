
FROM openjdk:17-jdk-alpine
MAINTAINER techmatrix18.com
COPY target/docker-java-jar-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM adoptopenjdk/openjdk11:ubi
#ARG JAR_FILE=target/*.jar
#ENV BOT_NAME=test.first_techmatrix18_bot
#ENV BOT_TOKEN=123456767ug:hnjyui545gwfvv43r234
#ENV BOT_DB_USERNAME=jpa_db_user
#ENV BOT_DB_PASSWORD=jpa_db_password
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]

