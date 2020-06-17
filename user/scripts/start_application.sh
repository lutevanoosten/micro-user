#!/usr/bin/env bash
set -e

CATALINA_HOME='/var/lib/tomcat8'
DEPLOY_TO_ROOT='true'

WAR_STAGED_LOCATION="/tmp/app.war"

# In Tomcat, ROOT.war maps to the server root
if [[ "$DEPLOY_TO_ROOT" = 'true' ]]; then
    CONTEXT_PATH='ROOT'
fi

# Remove unpacked application artifacts
if [[ -f $CATALINA_HOME/webapps/$CONTEXT_PATH.war ]]; then
    rm $CATALINA_HOME/webapps/$CONTEXT_PATH.war
fi
if [[ -d $CATALINA_HOME/webapps/$CONTEXT_PATH ]]; then
    rm -rfv $CATALINA_HOME/webapps/$CONTEXT_PATH
fi

# Copy the WAR file to the webapps directory
cp $WAR_STAGED_LOCATION $CATALINA_HOME/webapps/$CONTEXT_PATH.war

service tomcat8 start