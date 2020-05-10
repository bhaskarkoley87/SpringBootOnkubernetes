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

** If any above iteam is not installed in you machine, please see at the bottom. I have given the steps to install all items.

## **Steps to run the application on Kubernetes**
1. Clone the Github repository.
2. Build the code.
3. Build the Docker image and push to Docker hub.
4. Deploy the application and required services on Kubernetes.
5. Expose the application in Kubernetes.

### 1. Clone the Github repository

```$ git clone https://github.com/bhaskarkoley87/SpringBootOnkubernetes.git
```

### 2. Build the code

Open the ```cmd``` go to the repository directory 
1. ```cd StudentInfoService```
2. ```mvn clean install```
