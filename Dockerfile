# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the entire project source code into the container
COPY . .

# Install Maven inside the container
RUN apt update && apt install -y maven

# Build the application inside the container
RUN mvn clean package -DskipTests

# Expose the application port
EXPOSE 7071

# Run the application
ENTRYPOINT ["java", "-jar", "target/hotel-mgnt-0.0.1-SNAPSHOT.jar"]
