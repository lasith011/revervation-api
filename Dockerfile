FROM openjdk:11.0.11-jre-slim
ENV APP_DIR /application
ENV APP_FILE revervation-uber-jar.jar

EXPOSE 8080

WORKDIR $APP_DIR
COPY target/revervation-1.0-SNAPSHOT.jar $APP_DIR/$APP_FILE

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]