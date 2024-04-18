package ru.fildv.grpcmeteogeneratormicroservice.service;

import ru.fildv.grpcmeteogeneratormicroservice.model.Indicator;

import java.util.List;

public interface GIndicatorService {
    void send(Indicator indicator);

    void send(List<Indicator> indicators);
}
