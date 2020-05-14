# Spring Boot + Docker + Kubernetes
REST service in Spring-Boot running on Docker with Kubernetes orchestration

![Image of Spring Boot](https://github.com/bhaskarkoley87/SpringBootOnkubernetes/blob/master/images/SB_D_K.jpg)

### Some basic tools use as best practices and for code quality check

![Image of tools](https://github.com/bhaskarkoley87/SpringBootOnkubernetes/blob/master/images/Untitled-3.jpg)


**Project Intro:** This is a simple REST API develop with Spring Boot and using Docker Desktop I have deploy the application on Docker with Dockerfile, docker-compose.yml. I have use Kubernetes for the orchestration. In today's market all are in demand. I just want to show some of the tools requied at build phase. There are lost of tools and software are use in this phase, but I am just showing a small part. This development is done on the Windows machine.

I have also use some tools or API as a best practice which help developer for log tracking. I have also used Swagger. Swagger is an open-source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful web services.

For code quality check I have used the SonarQube which is for continuous inspection of code quality to perform automatic reviews with static analysis of code to detect bugs, code smells, and security vulnerabilities.

### Minimum requirement for this project
1. JDK 8+ installed
2. Git installed
3. Docker Desktop 19.03.8 with Kubernetes enabled
4. Maven installed
5. Any IDE for code edit
6. Profile creation in SonarCloud



## Steps to run the application on Kubernetes
1. Clone the Github repository.
2. Dependencis need to add for the application.
3. Configuration for Swagger.
4. Configuration for Log4j2.
5. Build the code.
6. Build for SonarCloude report for Code Quality gate.
7. Build the Docker image and push to Docker hub.
8. Deploy the application and required services on Kubernetes.
9. Expose the application in Kubernetes.
10. Final output from the Service.
11. Swagger UI output.


### 1. Clone the Github repository

  ```$ git clone https://github.com/bhaskarkoley87/SpringBootOnkubernetes.git```


### 2. Dependencis need to add for the application
 
 #### pom.xml
  ```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.7.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.bhk</groupId>
	<artifactId>StudentInfoService</artifactId>
	<version>0.0.1</version>
	<name>StudentInfoService</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<swagger.version>2.9.2</swagger.version>
		<swagger-annotations.version>1.5.21</swagger-annotations.version>
		<swagger-models.version>1.5.21</swagger-models.version>
		<sonar.java.coveragePlugin>jacoco</sonar.java.coveragePlugin>
		<sonar.dynamicAnalysis>reuseReports</sonar.dynamicAnalysis>
		<sonar.jacoco.reportPath>${project.basedir}/../target/jacoco.exec</sonar.jacoco.reportPath>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>

			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-log4j2</artifactId>
			<!-- <version>2.1.3.RELEASE</version> -->
		</dependency>
		<dependency>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>2.13.2</version>
			<type>pom</type>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>${swagger.version}</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>${swagger.version}</version>
		</dependency>
		<dependency>
			<groupId>io.swagger</groupId>
			<artifactId>swagger-annotations</artifactId>
			<version>${swagger-annotations.version}</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<configuration>
					<argLine>-javaagent:${sonar.jacoco.jar}=destfile=${sonar.jacoco.reportPath},includes=com.*</argLine>
					<includes>
						<include>**/*.class</include>
					</includes>
					<test>**/*.java</test>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.sonarsource.scanner.maven</groupId>
				<artifactId>sonar-maven-plugin</artifactId>
				<version>3.6.0.1398</version>
			</plugin>
			<plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<version>0.8.4</version>
			</plugin>
		</plugins>
	</build>

	<profiles>
		<profile>
			<id>coverage</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<build>
				<plugins>
					<plugin>
						<groupId>org.jacoco</groupId>
						<artifactId>jacoco-maven-plugin</artifactId>
						<executions>
							<execution>
								<id>prepare-agent</id>
								<goals>
									<goal>prepare-agent</goal>
								</goals>
							</execution>
							<execution>
								<id>report</id>
								<goals>
									<goal>report</goal>
								</goals>
							</execution>
						</executions>
					</plugin>
				</plugins>
			</build>
		</profile>
	</profiles>
</project>

  ```
  
  
### 3. Configuration for Swagger

  Define the Swagger configuration properties in application.properties.
  
  ```properties
  ## Service expose Port
  server.port=8080
  
  ## Swagger Properties
  api.version=1.0
  swagger.enabled=true
  swagger.title=Student Info API
  swagger.description=Sample Swagger implementation for the `Student Info API` service.
  swagger.useDefaultResponseMessages=false
  swagger.enableUrlTemplating=false
  swagger.deepLinking=true
  swagger.defaultModelsExpandDepth=1
  swagger.defaultModelExpandDepth=1
  swagger.displayOperationId=false
  swagger.displayRequestDuration=false
  swagger.filter=false
  swagger.maxDisplayedTags=0
  swagger.showExtensions=false
  ```
  

### 4. Configuration for Log4j2

  Create the log4j2.xml in the application class path, such as resources directory.
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <Configuration status="TRACE">
    <Appenders>
      <Console name="Console" target="SYSTEM_OUT">
        <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
      </Console>
    </Appenders>
    <Loggers>
      <Root level="trace">
        <AppenderRef ref="Console"/>
      </Root>
    </Loggers>
  </Configuration>

  ```


### 5. Build the code

Open the ```cmd``` go to the repository directory 
  1. ```cd StudentInfoService```
  2. ```mvn clean install```


### 6. Build for SonarCloude report for Code Quality gate

  Execute te following code on command prompt to run the SonarQube scan for code quality check and upload the report to the SonarCloud platform for the details analysed report. 
  
  ```
  mvn sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=<organization name created in Sonarcloud> -Dsonar.login=<SonarCloud tocken for login>
  
  ```
  
  In first run it will some time. After Build complete you can go to the SonarCloud and see the Scan report.
  ![Image of sc](https://github.com/bhaskarkoley87/SpringBootOnkubernetes/blob/master/images/Code%20Quality%20Gate.PNG)
  


### 7. Build the Docker image and push to Docker hub
 
 ```
 docker-compose push
 
 ```
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


### 8. Deploy the application and required services on Kubernetes

  ```
  kubectl apply -f k8s-compose.yaml
  
  ```
  
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

### 9. Expose the application in Kubernetes
 
  ```kubectl expose deployment studentinfoservice-deployment --type=LoadBalancer --port 8080 --target-port 8080```


### 10. Final output from the Service

![Image of output](https://github.com/bhaskarkoley87/SpringBootOnkubernetes/blob/master/images/service%20output.PNG)


### 11. Swagger UI output
After the application start we can see the Swagger Ui to understand the REST API better. Open the URL http://localhost:8080/swagger-ui.html#/ automatic![Swagger url](http://localhost:8080/swagger-ui.html#/)
	
![Image of swagger](https://github.com/bhaskarkoley87/SpringBootOnkubernetes/blob/master/images/swagger.PNG)


## **Profile creation in SonarCloud
..* Open https://sonarcloud.io/ and create your account.
..* Login to the SonarCloud account. 
..* To create the Organization go to the top right, click on the + icon and select "Create New Organization"
..* You will see the screen as below. Fill up the details and click "continue."
..* On the next screen, select a free plan and click on "create organization."
..* Click on "Create New Project," and on the next screen, you will have two tabs: "select repositories" and "create manually"
..* Click on "Configure Analysis" to create the token for login.
..* Generate the token and then copy your token.


@BretFisher Thank you for your tutorials....

@BretFisher What do you think about these updates?
