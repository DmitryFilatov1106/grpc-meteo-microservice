# Generator microservice

### <u>Run guide:</u>

#### To build the project:

* .\mvnw clean package
* if not yet...then create\
  docker network create grpc_net --driver bridge

#### To execute the app:

* *docker-compose up -d*

The Generator is running on port 8081 and has two endpoints:

* POST /api/v1/indicator/send\
  Example JSON

```
  {
      "meteoId": 2,                           
      "timestamp": "2024-04-18T18:15:40",
      "value": 40.0,
      "meteoType": "HUMIDITY"
  }  
```

* POST /api/v1/indicator/test/send\
  This endpoint simulates the operation of weather sensors and transmits data the Generator with a specified frequency\
  Example JSON

```
  {
      "delayInSeconds": 3,                           
      "meteoTypes": ["HUMIDITY","PRESSURE","TEMPERATURE"]
  }  
```

#### Example file <u>.env</u>:

GRPC_SERVER=server:8980\
BATCH_SIZE=3

<details>
<summary>Application view</summary>

![Screenshot](pictures/screen1.png)

</details>