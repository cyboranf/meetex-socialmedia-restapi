FROM openjdk:17

COPY target/meetexApi-0.0.1-SNAPSHOT.jar meetexApi-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/meetexApi-0.0.1-SNAPSHOT.jar"]