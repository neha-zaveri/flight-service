#!/usr/bin/env bash
cd ..
./gradlew clean build
docker build -t flight-service:latest .
docker tag flight-service:latest nehazaveri/flight-service
docker push nehazaveri/flight-service