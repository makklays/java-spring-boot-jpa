
# project in jar-file
FROM java:11
EXPOSE 8080
ADD /target/demo.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]

# for web application
#FROM tomcat8
#ADD sample.war ${CATALINA_HOME}/webapps/ROOT.war
#CMD ${CATALINA_HOME}/bin/catalina.sh run