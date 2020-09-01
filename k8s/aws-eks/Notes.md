aws eks --region us-east-2 update-kubeconfig --name eks-lb-example

aws eks --region us-east-2 create-cluster --name eks-lb-example --role-arn arn:aws:iam::922433728077:role/eksServiceRole --resources-vpc-config subnetIds=subnet-0b780423fc4e2a5c0,subnet-06c10c0fe2145ad6f,subnet-07f5d8039062d8194
```
{
    "cluster": {
        "name": "eks-lb-example",
        "arn": "arn:aws:eks:us-east-2:922433728077:cluster/eks-lb-example",
        "createdAt": 1556658199.353,
        "version": "1.12",
        "roleArn": "arn:aws:iam::922433728077:role/eksServiceRole",
        "resourcesVpcConfig": {
            "subnetIds": [
                "subnet-0b780423fc4e2a5c0",
                "subnet-06c10c0fe2145ad6f",
                "subnet-07f5d8039062d8194"
            ],
            "securityGroupIds": [],
            "vpcId": "vpc-0537b6030c0c5c27c",
            "endpointPublicAccess": true,
            "endpointPrivateAccess": false
        },
        "logging": {
            "clusterLogging": [
                {
                    "types": [
                        "api",
                        "audit",
                        "authenticator",
                        "controllerManager",
                        "scheduler"
                    ],
                    "enabled": false
                }
            ]
        },
        "status": "CREATING",
        "certificateAuthority": {},
        "platformVersion": "eks.1"
    }
}
```
status check
aws eks --region us-east-2 describe-cluster --name eks-lb-example --query cluster.status

kubectl-user/kubernetes-user1 - had to made administrator
Access Key ID: AKIA5NRKVVZGXA2GFPTX
Secret Access Key: 1tcFqSwnO9TbbUbshpkbi5pmnmplvo18Dkr0MDWu

