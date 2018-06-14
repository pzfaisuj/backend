FROM 8-jdk-alpine

EXPOSE 8095 8000
COPY target/cenuj-backend-1.0-SNAPSHOT.jar cenuj-backend.jar
CMD ["java","-Xdebug","-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000","-jar","lib/eat.jar"]