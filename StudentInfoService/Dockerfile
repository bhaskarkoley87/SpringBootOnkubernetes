# Starting with Docker base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Added Maintainer Info here. Details of Bhaskar Koley
LABEL maintainer="bhaskarkoley87@gmail.com"
# Added a volume pointing to /tmp
VOLUME /tmp/studentinfovalume
# This application will be accessible from port 8080 outside the container.
EXPOSE 8080
# Copying the jar file to workdir
WORKDIR /usr/app
COPY ./target/StudentInfoService-0.0.1.jar /usr/app
# Defined WORKDIR
# executing the jar file here...
ENTRYPOINT ["java","-jar","StudentInfoService-0.0.1.jar"]