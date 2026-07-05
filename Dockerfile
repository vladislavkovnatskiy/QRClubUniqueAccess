FROM maven:3.8.8-eclipse-temurin-21-alpine
LABEL authors="Vlad"

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install

EXPOSE 8085

CMD ["java", "-jar", "target/qrclubuniqueaccess-0.0.1-SNAPSHOT.jar"]