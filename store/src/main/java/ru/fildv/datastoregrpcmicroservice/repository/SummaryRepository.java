package ru.fildv.datastoregrpcmicroservice.repository;

import ru.fildv.datastoregrpcmicroservice.model.Indicator;
import ru.fildv.datastoregrpcmicroservice.model.MeteoType;
import ru.fildv.datastoregrpcmicroservice.model.Summary;
import ru.fildv.datastoregrpcmicroservice.model.SummaryType;

import java.util.Optional;
import java.util.Set;

public interface SummaryRepository {
    Optional<Summary> findByMeteoId(long meteoId, Set<MeteoType> meteoTypes, Set<SummaryType> summaryTypes);

    void save(Indicator indicator);
}