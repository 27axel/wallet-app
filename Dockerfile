FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/wallet-app-0.0.1-SNAPSHOT.jar wallet-app.jar
ENTRYPOINT ["java", "-jar", "wallet-app.jar"]
