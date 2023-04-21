FROM balenalib/raspberry-pi-debian-openjdk:8--stretch-build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Grocery_list-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","*/Grocery_list-0.0.1-SNAPSHOT.jar"]

#docker buildx build --platform linux/arm64 -t thedev03/list-arm:latest --push .