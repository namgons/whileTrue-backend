#!/bin/bash

ROOT_PATH="/home/ubuntu/app"

CONTAINER="app_container"
IMAGE="app_image"

NETWORK="custom-network"

docker build -t "$IMAGE" "$ROOT_PATH"
docker run -dp 443:80 --name "$CONTAINER" "$IMAGE"
docker network connect "$NETWORK" "$CONTAINER"