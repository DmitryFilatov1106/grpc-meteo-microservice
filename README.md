# gRPC meteo microservice

This web application receives data from weather sensors, and then processes and stores them (TEMPERATURE, HUMIDITY, PRESSURE).
The app consists from three microservices: Generator, Server, Store.
Microservices communicate with each other using gRPC.
The Generator gets data from meteo sensors in JSON format.
The Server receives data from the Generator and then transfers it to the Store.
The Store stores data in a redis database and can then provide analytics upon request from the Server.
