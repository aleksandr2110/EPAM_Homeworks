FROM adoptopenjdk/openjdk8

ENV APP_JAR_NAME="hm_13_docker-1.0-SNAPSHOT.jar"

WORKDIR /opt
COPY target/$APP_JAR_NAME $APP_JAR_NAME

EXPOSE 8080

ENTRYPOINT  ["java",  "-jar", "hm_13_docker-1.0-SNAPSHOT.jar"]