#制作应用docker 镜像
FROM openjdk:8-jdk-alpine3.8
MAINTAINER dyh dyh1183@163.com
# pom.xml中配置jar文件
ARG JAR_FILE
COPY  target/${JAR_FILE}  /opt/app/video.jar
WORKDIR /opt/app
VOLUME /opt/app
EXPOSE 8081
ENTRYPOINT ["java","-jar","/opt/app/video.jar"]