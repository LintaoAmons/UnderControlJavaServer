#!/usr/bin/env bash
set -e

declare -r version="0.0.7-SNAPSHOT"
declare -r imageId="oatnil.top:8083/undercontrol-server:$version"

printf "\n %s\n" "👻 start compile code base"
./mvnw clean install -DskipITs

printf "\n %s\n" "👻 start BUILDING image: $imageId"
printf "\n %s\n" "command: [docker build -t $imageId .]"
docker build -t "$imageId" .

printf "\n %s\n" "👻 start PUSHING image: $imageId"
printf "\n %s\n" "command: [docker push $imageId]"
docker push "$imageId"

printf "\n %s\n" " ✅ Finished"
