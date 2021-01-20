# Flight-Service
### To Run service on local
1. ./scripts/start-dynamodb.sh 
2. ./gradlew clean build
3. ./gradlew bootRun 

http://localhost:8081/flight-service/actuator/health
It should give below output \
`{
     "status": "UP",
     "components": {
         "diskSpace": {
             "status": "UP",
             "details": {
                 "total": 250790436864,
                 "free": 87013748736,
                 "threshold": 10485760,
                 "exists": true
             }
         },
         "ping": {
             "status": "UP"
         }
     }
 }`

### To deploy application to kubernetes
####### Note: Assumption is minikube is already running and dynamoDB is also running inside a pod on kubernetes 
1. cd scripts
2. ./docker-images.sh
3. ./kubectl-deploy-local.sh

After this if you have ingress gateway already running
http://paathshala.com/flight-service/actuator/health
should give \
`{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 18211586048,
                "free": 14563147776,
                "threshold": 10485760,
                "exists": true
            }
        },
        "livenessState": {
            "status": "UP"
        },
        "ping": {
            "status": "UP"
        },
        "readinessState": {
            "status": "UP"
        }
    },
    "groups": [
        "liveness",
        "readiness"
    ]
}`

#Kubernetes On AWS
Follow below to configure eksctl \
https://docs.aws.amazon.com/eks/latest/userguide/getting-started-eksctl.html

Navigate to scripts/aws
Execute `./deploy-cluster.sh`

Ref: 
https://docs.aws.amazon.com/eks/latest/userguide/getting-started-eksctl.html

Once this get deployed

./pre-ingress-setup.sh

Ref:
https://docs.aws.amazon.com/eks/latest/userguide/alb-ingress.html


