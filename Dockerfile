FROM tomcat:latest
ADD /build/libs/stoom.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
