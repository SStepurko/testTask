# define base docker image
FROM openjdk:17
# copy files
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} my-app.jar
# how to run
ENTRYPOINT ["java","-jar","/my-app.jar"]
# port for application
EXPOSE 7000
