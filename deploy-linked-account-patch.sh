#!/usr/bin/env bash
CAS_SOURCE_PATH=/home/dushan/projects/personal/cas
CAS_DEPLOY=/home/dushan/softwares/tomcat/apache-tomcat-8.0.35/webapps/cas/WEB-INF

cp $CAS_SOURCE_PATH/cas-server-support-jdbc/build/libs/cas-server-support-jdbc-4.2.6.jar $CAS_DEPLOY/lib 
cp $CAS_SOURCE_PATH/cas-server-core-authentication/build/libs/cas-server-core-authentication-4.2.6.jar $CAS_DEPLOY/lib
cp $CAS_SOURCE_PATH/cas-server-core-api-authentication/build/libs/cas-server-core-api-authentication-4.2.6.jar $CAS_DEPLOY/lib
cp $CAS_SOURCE_PATH/cas-server-webapp-actions/build/libs/cas-server-webapp-actions-4.2.6.jar $CAS_DEPLOY/lib
cp $CAS_SOURCE_PATH/cas-server-core-web/build/libs/cas-server-core-web-4.2.6.jar $CAS_DEPLOY/lib

cp $CAS_SOURCE_PATH/cas-server-webapp/src/main/webapp/WEB-INF/webflow/login/login-webflow.xml $CAS_DEPLOY/webflow/login
cp $CAS_SOURCE_PATH/cas-server-webapp/src/main/webapp/WEB-INF/view/jsp/default/ui/casLinkedAccountSelectionView.jsp $CAS_DEPLOY/view/jsp/default/ui
