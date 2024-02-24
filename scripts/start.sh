#!/bin/bash

ROOT_PATH="/home/ubuntu/app"

CONTAINER="app_container"
IMAGE="app_image"

docker build -t "$IMAGE" "$ROOT_PATH"
docker run -dp 80:80 --name "$CONTAINER" "$IMAGE"