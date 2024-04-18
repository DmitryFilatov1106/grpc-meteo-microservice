package ru.fildv.grpcmeteogeneratormicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcMeteoGeneratorMicroserviceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(
                GrpcMeteoGeneratorMicroserviceApplication.class, args);
    }
}
