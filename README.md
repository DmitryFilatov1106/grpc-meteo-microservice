# gRPC meteo microservices

This web application receives data from weather sensors, and then processes and stores them (TEMPERATURE, HUMIDITY, PRESSURE).
The app consists from three microservices: Generator, Server, Store.
Microservices communicate with each other using gRPC.
The Generator gets data from meteo sensors in JSON format.
The Server receives data from the Generator and save data in database, also Server transfers data to the Store.
The Store stores aggregate data in a redis database and then can provide analytics upon request from users.
The Store can provide aggregate data of the following types: MIN, MAX, AVG, SUM

### <u>Run guide:</u>

See the subfolders for assembling and launching microservices.

### <u>Technology stack:</u>

* Java: JDK 17
* Frameworks: Spring boot 3 (web, data-jpa), gRPC
* Build: Maven
* Mapping: Mapstruct
* ORM: Hibernate
* DB: Postgres, Redis
* Migrations: Liquibase
* Containers: Docker, docker-compose
* Library: Lombok

