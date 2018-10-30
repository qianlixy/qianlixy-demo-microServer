FROM docker.io/openjdk:8-jre

MAINTAINER qianli_xy@163.com

EXPOSE 8800

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/eureka.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /eureka.jar