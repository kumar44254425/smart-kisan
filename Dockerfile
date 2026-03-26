FROM tomcat:9.0

COPY . /usr/local/tomcat/webapps/smartkisan/

EXPOSE 8080