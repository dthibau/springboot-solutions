FROM    alpine:3.7
RUN apk --update add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]

