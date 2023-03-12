#!/usr/bin/env bash
if [ -z "$1" ]
  then
    echo "No argument supplied"
    exit
fi
echo "Running command: docker-compose run --rm automation-tests ./run-maven.sh $@"
docker-compose run --rm automation-tests ./run-maven.sh $@