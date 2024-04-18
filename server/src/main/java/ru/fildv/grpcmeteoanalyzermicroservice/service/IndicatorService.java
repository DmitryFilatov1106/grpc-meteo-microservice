package ru.fildv.grpcmeteoanalyzermicroservice.service;

import ru.fildv.grpcmeteoanalyzermicroservice.model.Indicator;

import java.util.List;

public interface IndicatorService {
    void save(Indicator indicator);

    List<Indicator> get(long batchSize);
}
