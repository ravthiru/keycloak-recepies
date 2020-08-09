#!/usr/bin/env bash

set -e

### constants ##################################################################

this_path="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root_path="$(cd "$this_path/.." && pwd)"

PYTHON_VERSION="3.8.5"
ROBOT_VERSION="3.2.1"
IMAGE_NAME="robotframework"

### variables ##################################################################

: "${BUILD_ARGS:=""}"
: "${BUILD_DIR:="$repo_root_path"}"

### build image ######################################################################


docker build \
  --file "$this_path/Dockerfile" \
  --pull \
  --no-cache \
  --build-arg PYTHON_VERSION=$PYTHON_VERSION \
  --build-arg ROBOT_VERSION=$ROBOT_VERSION \
  --tag "$IMAGE_NAME:$ROBOT_VERSION" \
  "$BUILD_DIR"
 
