#!/bin/bash

CONTAINER="app_container"
IMAGE="app_image"

NETWORK="custom-network"

if docker container inspect "$CONTAINER" >/dev/null 2>&1; then
    docker network disconnect "$NETWORK" "$CONTAINER"
    echo "container exists locally"
    docker stop "$CONTAINER"
    docker rm "$CONTAINER"
else
    echo "container does not exist locally"
fi
if docker image inspect "$IMAGE" >/dev/null 2>&1; then
    echo "Image exists locally"
    docker rmi "$IMAGE"
else
    echo "Image does not exist locally"
fi