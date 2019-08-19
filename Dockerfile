FROM openjdk:8-jre-alpine

ARG DEPENDENCY=target/
ARG JAR_FILE

EXPOSE 8080

COPY ${DEPENDENCY}/${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "-XshowSettings:vm", "application.jar"]