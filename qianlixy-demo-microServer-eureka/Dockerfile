FROM docker.io/openjdk:8-jre

MAINTAINER qianli_xy@163.com

ARG JAR_FILE
ADD target/${JAR_FILE} /${JAR_FILE}

EXPOSE 8800

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "${JAR_FILE}"]