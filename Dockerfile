FROM amazoncorretto:21.0.2-alpine3.19
COPY target/*.jar listener-app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/listener-app.jar"]