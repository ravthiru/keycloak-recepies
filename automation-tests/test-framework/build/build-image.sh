#!/bin/bash 

set -e

### constants ##################################################################

this_path="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

repo_root_path="$(cd "$this_path/.." && pwd)"


NEWMAN_VERSION="5.1.2-alpine"

IMAGE_NAME="newman-docker/newman-htmlextra"

IMAGE_VERSION="1.0"

### variables ##################################################################

: "${BUILD_ARGS:=""}"

: "${BUILD_DIR:="$repo_root_path"}"

### build ######################################################################

docker build  --file "$this_path/Dockerfile"  --pull --no-cache --build-arg NEWMAN_VERSION=$NEWMAN_VERSION --tag "$IMAGE_NAME:$IMAGE_VERSION"  "$BUILD_DIR"
