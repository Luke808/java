FROM java:8
WORKDIR /apps
COPY masterdata-starter.jar ./smsf-service.jar
COPY application*.yml ./application.yml
COPY bootstrap.yml ./bootstrap.yml
COPY ./lib ./lib
ENTRYPOINT [ "java","-Xmx1024m","-Xms1024m","-jar","./smsf-service.jar" ]