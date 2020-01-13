#制作应用docker 镜像
FROM openjdk:8u92-jre-alpine
MAINTAINER dyh dyh1183@163.com
# pom.xml中配置jar文件
ARG JAR_FILE
COPY  target/${JAR_FILE}  /opt/app/video.jar
WORKDIR /opt/app
VOLUME /opt/app
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app/video.jar"]