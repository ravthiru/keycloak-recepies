#!/bin/bash -x

#
# This script sets up the .curlrc file so that you can use curl to access
# services which require a bearer authorisation token.
#
#
# USAGE:
#   login user
#
# EXAMPLE:
#    login alen.tuning
#
# Client is course-management
# Realm is university .
#

umask 077
USERNAME=$1
CLIENT=curl

REALM=university


# Where to save our stuff.
OIDC=$HOME/.oidc
mkdir -p $OIDC

function save() {
  JSON=$1
  ERROR=`expr "$JSON" : '.*error_description'`
  if [ $ERROR -ne 0 ]
  then
    echo $JSON
    return 1
  fi

  ACCESS_TOKEN=`echo $JSON | sed 's/.*access_token":"//g' | sed 's/".*//g'`
  REFRESH_TOKEN=`echo $JSON | sed 's/.*refresh_token":"//g' | sed 's/".*//g'`

  # Save the original JWT for debugging
  echo "$JSON" > $OIDC/json_token

  # Save the access and refresh tokens for later.
  echo "$REFRESH_TOKEN" > $OIDC/refresh_token
  echo "$ACCESS_TOKEN" > $OIDC/access_token

  # Output the headers and defaults for curl
  echo "header = \"Authorization: bearer $ACCESS_TOKEN\"" > ~/.curlrc
  echo "silent" >> ~/.curlrc
}

#
# Attempt to refresh the access_token.
# Returns 1 if unsuccessful for any reason.
#
function refresh() {
  # There is no access token?
  if [ ! -f $OIDC/access_token ]
  then
    return 1
  fi

  # There is no refresh token?
  if [ ! -f $OIDC/refresh_token ]
  then
    return 1
  fi

  NOW=`date +%s`
  
  REFRESH_TOKEN_CREATED=`stat -c "%Y" $OIDC/refresh_token`
  REFRESH_TOKEN_AGE=`expr $NOW - $REFRESH_TOKEN_CREATED`

  # The refresh token has expired?
  if [ $REFRESH_TOKEN_AGE -gt 1800 ]
  then
    return 1
  fi

  ACCESS_TOKEN_CREATED=`stat -c "%Y" $OIDC/access_token`
  ACCESS_TOKEN_AGE=`expr $NOW - $ACCESS_TOKEN_CREATED`

  # The access token is more than 240 seconds old?
  if [ $ACCESS_TOKEN_AGE -gt 240 ]
  then
    echo "Refreshing access token" 1>&2
    REFRESH_TOKEN=`cat $OIDC/refresh_token`
    JSON=`curl --insecure  -X POST -d "client_id=$CLIENT&refresh_token=$REFRESH_TOKEN&grant_type=refresh_token" http://localhost:8080/auth/realms/$REALM/protocol/openid-connect/token`
    save "$JSON"
    return 0
  fi

  # The access token is still valid. Yay!
  return 0
}

if refresh
then
  exit 0
fi

echo -n "Password: "
read -s PASSWORD
echo

#curl --data "grant_type=password&client_id=curl&username=user&password=password" http://localhost:8180/auth/realms/master/protocol/openid-connect/token

JSON=`curl --insecure  -s --data "grant_type=password&client_id=$CLIENT&username=$USERNAME&password=$PASSWORD" http://localhost:8080/auth/realms/$REALM/protocol/openid-connect/token`
#echo $JSON
save "$JSON"
