#!/bin/bash
set -e

### constants ##################################################################

this_path="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"


### Run Test cases ###################################################



docker run --rm --net=host -v "$this_path/tests":/etc/newman -t newman-docker/newman-htmlextra:1.0  run "springboot_keycloak_collection.json"  --environment="dev_environment.json" --reporters="cli,htmlextra" --insecure  --reporter-htmlextra-export="newman-results.html"


