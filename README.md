# Eat club technical round 2 challenge

# Development

## Requirements
- java 21

## Profile
Run the app under `local` profile.

```
-Dspring.profiles.active=local
``` 

## Lombok

Enable annotation processing.

![test](lombok.png)

# Coding best practices
- Constructor dependency injection rather than @Autowired
- Lombok to simplify boilerplate code
- Separation of DTO and domain classes
- Immutability using records
- Implementing interfaces for both mappers, and time parser for extensibility and flexibility in future changes
- Unit tests, sliced testing i.e webmvc
- Centralized exception handling
- RFC 7807 specification for problem responses 
- spring profiles and cascading properties

# Deploy to aws
Follow this guide
https://aws.amazon.com/blogs/opensource/github-actions-aws-fargate/

Create ECR repository
```
aws ecr create-repository --repository-name eatclub --region ap-southeast-2
```

Create task definition 
```
aws ecs register-task-definition --generate-cli-skeleton > task-definition.json
```

Create log group
```
aws logs create-log-group --log-group-name /ecs/eatclub --region ap-southeast-2
aws logs put-retention-policy --log-group-name /ecs/eatclub --retention-in-days 5 --region ap-southeast-2
```

Register task definition
```
aws ecs register-task-definition --region ap-southeast-2 --cli-input-json file://task-definition.json
```

Create cluster
```
aws ecs create-cluster --region ap-southeast-2 --cluster-name eatclub
```

Creating fargate service
```
First, find the subnet:
aws ec2 describe-subnets --region ap-southeast-2 --query "Subnets[*].SubnetId"
aws ecs create-service --region ap-southeast-2 --service-name eatclub-service --cluster eatclub --task-definition eatclub --desired-count 1 --launch-type "FARGATE" --network-configuration "awsvpcConfiguration={subnets=[subnet-9f46a6f9,subnet-3724397e,subnet-9fee7cc7],securityGroups=[sg-d706afa7]}"
```

# Recommended improvements

- Integration test with mock server for restaurant client
- Consider making restaurant an interface and use generics for greater flexibility
- GPG signing for main branch release
- Create different aws environments i.e dev cluster, prd cluster. 
- Document and automate creation of aws resources. Or use CDK :)
- Expose API via a gateway
- Create a separate user on AWS with different roles rather than using my own
