# Use OpenJDK 22 as the base image
FROM openjdk:23-slim

# Set the working directory inside the container
WORKDIR /docapp

# Copy the Maven POM file to the working directory
COPY target/FullStack-1.0-SNAPSHOT.jar .

EXPOSE 8080

# Define the command to run the Java application
CMD ["java", "-jar", "FullStack-1.0-SNAPSHOT.jar"]
