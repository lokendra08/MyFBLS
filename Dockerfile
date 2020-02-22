FROM alpine:3.4

WORKDIR /app

# The application's jar file
ARG JAR_FILE=target/FBLS-*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} FBLS.jar

EXPOSE 6060
EXPOSE 6061

#ENTRYPOINT ["java", "-jar", "/app/FBLS.jar"]
ENTRYPOINT exec java -XX:+UseG1GC -Xmx3584M  -jar /app/FBLS.jar

