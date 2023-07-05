FROM openjdk:17
COPY /target/hello-wsl-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8080
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "hello-wsl-0.0.1-SNAPSHOT.jar"]