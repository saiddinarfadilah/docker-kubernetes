FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
ADD ./pom.xml /app
ADD ./src /app/src
RUN mvn clean package

FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/source-0.0.1-SNAPSHOT.jar /app/source-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/source-0.0.1-SNAPSHOT.jar"]