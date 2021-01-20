#!/usr/bin/env bash
kubectl delete -f deploy-flight.yaml
kubectl create -f deploy-flight.yaml
