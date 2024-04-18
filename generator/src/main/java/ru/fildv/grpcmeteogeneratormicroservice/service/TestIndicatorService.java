package ru.fildv.grpcmeteogeneratormicroservice.service;

import ru.fildv.grpcmeteogeneratormicroservice.model.test.IndicatorTestOptions;

public interface TestIndicatorService {
    void sendMessages(IndicatorTestOptions testOptions);
}
