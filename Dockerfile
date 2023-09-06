#
# Build stage
#
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jre
COPY --from=build /home/app/target/sistemaacademico-0.0.1-SNAPSHOT.jar /usr/local/lib/sistemaacademico-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/sistemaacademico-0.0.1-SNAPSHOT.jar"]