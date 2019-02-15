FROM openjdk:11
WORKDIR /apps
COPY masterdata-starter.jar ./smsf-service.jar
COPY application*.yml ./
COPY bootstrap.yml ./
COPY ./lib ./lib
ENV PROFILE prod
ENTRYPOINT java -Xmx1024m -Xms1024m -jar ./smsf-service.jar --spring.profiles.active=$PROFILE
ENV CONFIG_SERVER https://dlhpvva0002.dir.svc.accenture.com/smsf-config/
ENTRYPOINT java -Xmx1024m -Xms1024m -Dspring.profiles.active=$PROFILE -Dspring.cloud.config.uri=$CONFIG_SERVER -jar ./smsf-service.jar
