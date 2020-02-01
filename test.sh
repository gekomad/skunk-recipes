#!/bin/bash

RED='\033[0;31m'
BLUE='\033[0;34m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

printf "${BLUE}\nIs your user able to run docker? (sudo usermod -aG docker $USER)${NC}\n\n"

echo "stop skunk_recipies docker image..."
docker rm -f skunk_recipies
echo "start skunk_recipies docker image..."
docker run -d --name skunk_recipies -p5436:5432 -e POSTGRES_USER=jimmy -e POSTGRES_DB=world -e POSTGRES_PASSWORD=banana tpolecat/skunk-world
sleep 5
echo "test..."
sbt test
echo "stop skunk_recipies docker image..."
docker rm -f skunk_recipies
