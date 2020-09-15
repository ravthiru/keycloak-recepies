#!/usr/bin/env bash

set -e

### constants ##################################################################

path="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

KEYCLOAK_VERSION="11.0.2"
IMAGE_NAME="quay.io/keycloak/keycloak"

 
### Run Test cases ###################################################

chmod a+x  $path/config/keycloak-init.sh

docker stop keycloak || true

docker run --rm -itd -p 8080:8080 \
            --name=keycloak  \
           -e  KEYCLOAK_USER=admin \
           -e  KEYCLOAK_PASSWORD=admin \
           -v "$path/config":/opt/jboss/config \
            "$IMAGE_NAME:$KEYCLOAK_VERSION" 

docker  exec -it  --user 1000 keycloak  /usr/bin/sh -c  /opt/jboss/config/keycloak-init.sh


