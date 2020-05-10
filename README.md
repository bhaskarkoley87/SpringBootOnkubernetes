# Spring Boot + Docker + Kubernetes
REST service in Spring-Boot running on Docker with Kubernetes orchestration

![Image of Spring Boot](https://github.com/bhaskarkoley87/SpringBootOnkubernetes/blob/master/images/SB_D_K.jpg)


**NOTE:** This is a simple REST API develop with Spring Boot and using Docker Desktop I have deploy the application on Docker with Dockerfile, docker-compose.yml. I have use Kubernetes for the orchestration. In today's market all are in demand. I just want to show some of the tools requied at build phase. There are lost of tools and software are use in this phase, but I am just showing a small part. This development is done on the Windows machine.

## Minimum requirement for this project
1. JDK 8+ installed
2. Git installed
3. Docker Desktop 19.03.8 with Kubernetes enabled
4. Maven installed
5. Any IDE for code edit

** If any above item is not installed in you machine, please see at the bottom. I have given the steps to install all items.


## Steps to run the application on Kubernetes
1. Clone the Github repository.
2. Build the code.
3. Build the Docker image and push to Docker hub.
4. Deploy the application and required services on Kubernetes.
5. Expose the application in Kubernetes.

@BretFisher What do you think about these updates?
### 1. Clone the Github repository

  ```$ git clone https://github.com/bhaskarkoley87/SpringBootOnkubernetes.git```


### 2. Build the code

Open the ```cmd``` go to the repository directory 
  1. ```cd StudentInfoService```
  2. ```mvn clean install```


### 3. Build the Docker image and push to Docker hub
 
 ```docker-compose push```
 This command will docker-compose.yml to build the docker image using Dockefile in local directory.
 #### a. docker-compose.yml
 ```yaml
 version: "3.7"
  services:
     studentinfoservice:
        build:
           context: .
           dockerfile: Dockerfile
           network: MyStudentInfoNetwork
        image: bhaskarkoley87/studentinfo:latest
        ports:
           - "8080:8080"
        volumes:
         - app-data:/var/lib/data

  volumes:
    app-data:
 ```
 #### b. Dockerfile
 ```properties
 
 # Starting with Docker base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Added Maintainer Info here. Details of Bhaskar Koley
LABEL maintainer="bhaskarkoley87"
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
 ```


### 4. Deploy the application and required services on Kubernetes

  ```kubectl apply -f k8s-compose.yaml```
  
  This command use the k8s-compose.yaml file to pull the Docker image from Docker hub and deploy the docker image with 3 replica and    create a NodePort service in Kubernetes.
  
  ```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: studentinfoservice-deployment
    namespace: default
    labels:
      app: studentinfo
  spec:
    replicas: 3
    selector:
      matchLabels:
        app: studentinfo
    template:
      metadata:
        labels:
          app: studentinfo
      spec:
        containers:
        - name: studentinfos
          image: bhaskarkoley87/studentinfo:latest
          ports:
          - containerPort: 8080
  ---
  apiVersion: v1
  kind: Service
  metadata:
    name: studentinfoservice
    labels:
      app: studentinfo
      tier: fullstack
      role: master
  spec:
    type: NodePort
    ports:
    - port: 8080
      targetPort: 8080
    selector:
      app: studentinfo
      role: master
      tier: fullstack      
 
  ```

### 5. Expose the application in Kubernetes
 
  ```kubectl expose deployment studentinfoservice-deployment --type=LoadBalancer --port 8080 --target-port 8080```


@BretFisher Thank you for you tutotails....
