# Automatisation

This service is intended to work as a rest service on a resperry pi for home a private home automatisation project

For Testing and Debugging run docker:
docker run --rm   -e JPDA_ADDRESS=8000   -e JPDA_TRANSPORT=dt_socket   -p 8888:8080   -p 9000:8000   -v /home/marc/Documents/tomcat-users.xml:/usr/local/tomcat/conf/tomcat-users.xml   tomcat:8.0   /usr/local/tomcat/bin/catalina.sh jpda run

After you just need to login in http://localhost:8888/manager/html/
With your configuration
