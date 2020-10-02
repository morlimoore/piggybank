#Get jdk image
FROM openjdk:latest

COPY pom.xml /build/

WORKDIR /build/

COPY src /build/src/

#Add the built JAR file to the docker container
ADD target/piggybank-v1.jar /build/piggybank-v1.jar

#Provide port information
EXPOSE 8443

#Command tells docker to run the java file of type war named piggybank-v1.jar in the container
ENTRYPOINT ["java", "-jar", "/build/piggybank-v1.jar"]
