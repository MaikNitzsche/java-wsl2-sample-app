FROM openjdk:17
COPY /target/HelloMsg-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 49999
WORKDIR /tmp
CMD ["java", "-jar", "HelloMsg-0.0.1-SNAPSHOT.jar"]