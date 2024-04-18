# Store microservice

### <u>Run guide:</u>

#### To build the project:

* .\mvnw clean package
* if not yet...then create\
  docker network create grpc_net --driver bridge

#### To execute the app:

* *docker-compose up -d*

The Store is running on port 8083 and has one endpoints:
* GET /api/v1/analytics/summary/{meteoId}\

with parameters:
* <u>mt</u> (TEMPERATURE, HUMIDITY, PRESSURE) 
* <u>st</u> (MIN, MAX, AVG, SUM)

#### Example file <u>.env</u>:
REDIS_HOST=redis\
REDIS_PORT=6379\
GRPC_SERVER=server:8980\
BATCH_SIZE=3

<details>
<summary>Application view</summary>

![Screenshot](pictures/screen1.png)

</details>