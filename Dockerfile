FROM openjdk:17
COPY /target/hello-wsl-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 49999
WORKDIR /tmp
CMD ["java", "-jar", "hello-wsl-0.0.1-SNAPSHOT.jar"]