FROM tomcat:9.0

RUN rm -rf /usr/local/tomcat/webapps/*

COPY . /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080