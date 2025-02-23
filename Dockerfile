# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Install Maven inside the container
RUN apt update && apt install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the entire project source code into the container
COPY . .

# Build the application inside the container
RUN mvn clean package -DskipTests

# Verify that the target directory contains the JAR
RUN ls -l target/

# Expose the application port
EXPOSE 7071

# Run the application
CMD ["sh", "-c", "ls -l target/ && java -jar target/*.jar"]
