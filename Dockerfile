FROM openjdk:latest
LABEL owner="Brennan"

ADD target/godsofwargame2-0.0.1-SNAPSHOT.jar /usr/src/
EXPOSE 8080
CMD ["java", "-jar", "/usr/src/godsofwargame2-0.0.1-SNAPSHOT.jar"]