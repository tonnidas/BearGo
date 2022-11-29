FROM maven:3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:8
COPY --from=build /app/target/beargo-backend-0.0.1-SNAPSHOT.jar app.jar  
ENTRYPOINT ["java","-jar","app.jar"]