# Use a lean Java Runtime Environment (JRE) base image for security and size optimization.
# eclipse-temurin provides reliable, open-source Java builds.
FROM eclipse-temurin:17-jre-focal as final

# Set the working directory inside the container
WORKDIR /app

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

COPY target/post-api-0.0.1-SNAPSHOT.jar app.jar

# Define the command to execute your application when the container starts.
# 'java -jar /app.jar' is the standard way to run a Spring Boot executable JAR.
ENTRYPOINT ["java", "-jar", "app.jar"]
