# Server microservice

### <u>Run guide:</u>

#### To build the project:

* .\mvnw clean package
* if not yet...then create\
  docker network create grpc_net --driver bridge

#### To execute the app:

* *docker-compose up -d*

The Server is running on port 8082

#### Example file <u>.env</u>:
POSTGRES_HOST=dbase:5432\
POSTGRES_DB=meteo_gprc\
POSTGRES_USER=postgres\
POSTGRES_PASSWORD=post\
GRPC_SERVER=8980\
GENERATOR_BATCH_SIZE=3\
STORE_BATCH_SIZE=3