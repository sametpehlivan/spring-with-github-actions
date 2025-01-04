## Build Stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build

# Copy Maven configuration and fetch dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests


## Runtime Stage
FROM amazoncorretto:17
WORKDIR /app

# Build arguments
ARG APP_VERSION=1.0.0
ARG APP_DATABASE=customers
ARG APP_MONGO_HOST=mongodb
ARG APP_MONGO_PORT=27017

# Copy the built JAR from the build stage
COPY --from=build /build/target/github-actions-*.jar /app/app.jar

# Expose the application port
EXPOSE 8080

ENV MONGO_USERNAME=missing_user_name
ENV MONGO_PASSWORD=missing_password
ENV MONGO_AUTH_DATABASE=missing_auth_database
ENV MONGO_HOST=${APP_MONGO_HOST}
ENV MONGO_PORT=${APP_MONGO_PORT}
ENV MONGO_DATABASE=${APP_DATABASE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -Dspring.data.mongodb.username=${MONGO_USERNAME} \
              -Dspring.data.mongodb.password=${MONGO_PASSWORD} \
              -Dspring.data.mongodb.host=${MONGO_HOST} \
              -Dspring.data.mongodb.port=${MONGO_PORT} \
              -Dspring.data.mongodb.database=${MONGO_DATABASE} \
              -Dspring.data.mongodb.authentication-database=${MONGO_AUTH_DATABASE} \
              -jar  app.jar
## docker build -t customer-service/customer-service:1.0.0 -f ./Dockerfile .