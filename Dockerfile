## Build
FROM maven:3.8.7-openjdk-18 AS BUILD
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

## Runtime
FROM amazoncorretto:17
#define few things
ARG APP_VERSION=1.0.0
ARG APP_USERNAME=root
ARG APP_PASSWORD=password
ARG APP_HOST=mongodb
ARG APP_PORT=27017
ARG APP_DATABASE=customers
ARG APP_AUTH_DATABASE=admin

WORKDIR /app
COPY --from=build /build/target/github-actions-*.jar /app/
EXPOSE 8080

ENV MONGO_USERNAME=${APP_USERNAME}
ENV MONGO_PASSWORD=${APP_PASSWORD}
ENV MONGO_HOST=${APP_HOST}
ENV MONGO_PORT=${APP_PORT}
ENV MONGO_DATABASE=${APP_DATABASE}
ENV MONGO_AUTH_DATABASE=${APP_AUTH_DATABASE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -Dspring.data.mongodb.username=${MONGO_USERNAME} \
              -Dspring.data.mongodb.password=${MONGO_PASSWORD} \
              -Dspring.data.mongodb.host=${MONGO_HOST} \
              -Dspring.data.mongodb.port=${MONGO_PORT} \
              -Dspring.data.mongodb.database=${MONGO_DATABASE} \
              -Dspring.data.mongodb.authentication-database=${MONGO_AUTH_DATABASE} \
              -jar  github-actions-${JAR_VERSION}.jar
## docker build -t customer-service/customer-service:1.0.0 -f ./Dockerfile .