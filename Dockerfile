FROM maven:3-openjdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/banking-0.0.1-SNAPSHOT.jar /usr/local/lib/banking.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/banking.jar"]
