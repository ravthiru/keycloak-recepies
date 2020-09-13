#!/bin/bash

set -e
set -x
set -o pipefail

PATH=/opt/jboss/keycloak/bin:$PATH


authurl=http://localhost:8080/auth

authcmd() {
  kcadm.sh config credentials --server $authurl --realm master --user admin --password admin
}

echo Waiting for Keycloak to become ready while we try and get an access token
while ! authcmd; do
  sleep 3
done

echo Logged in

echo Configuring Keycloak...

kcadm.sh create realms -s realm=springboot-keycloak -s id=springbook-keycloak  -s displayName=springboot-keycloak  -s enabled=true -s sslRequired=external 

kcadm.sh create clients -r springboot-keycloak -s bearerOnly=true -s clientId=employee-service-app  -s enabled=true

kcadm.sh create clients -r springboot-keycloak -s clientId=automation-tester -s publicClient=true -s protocol=openid-connect -s "redirectUris=[\"http://localhost:8081/*\"]" -s enabled=true 

#kcadm.sh create roles -r springboot-keycloak -s name=user -o -s 'description=Allows access to get employee details.'


kcadm.sh create users -r springboot-keycloak -s username=test-user -s enabled=true

kcadm.sh set-password -r springboot-keycloak --username test-user --new-password  test123


#kcadm.sh add-roles --uusername test-user --rolename user -r  springboot-keycloak
