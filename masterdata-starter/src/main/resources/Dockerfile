FROM openjdk:11
WORKDIR /apps
COPY masterdata-starter.jar ./smsf-service.jar
COPY application*.yml ./
COPY bootstrap.yml ./
COPY ./lib ./lib
RUN mkdir -p /usr/local/share/ca-certificates/extra
ENV PROFILE dev
ENV CONFIG_SERVER https://dlhpvva0002.dir.svc.accenture.com/smsf-config/
ENTRYPOINT update-ca-certificates && java -Xmx1024m -Xms1024m -Dspring.profiles.active=$PROFILE -Dspring.cloud.config.uri=$CONFIG_SERVER -jar ./smsf-service.jar
