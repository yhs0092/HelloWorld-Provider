FROM swr.cn-north-1.myhuaweicloud.com/yhs0092/my-openjdk:0.0.1

WORKDIR /home/apps/

COPY target/lib lib

COPY target/*.jar app.jar

RUN sh -c 'touch app.jar'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar -Xmx256m app.jar" ]