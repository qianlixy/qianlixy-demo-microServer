FROM docker.io/java:8

MAINTAINER qianli_xy@163.com

EXPOSE 8761

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/eureka.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /eureka.jar
