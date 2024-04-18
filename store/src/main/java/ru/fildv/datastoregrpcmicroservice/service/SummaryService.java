package ru.fildv.datastoregrpcmicroservice.service;

import ru.fildv.datastoregrpcmicroservice.model.Indicator;
import ru.fildv.datastoregrpcmicroservice.model.MeteoType;
import ru.fildv.datastoregrpcmicroservice.model.Summary;
import ru.fildv.datastoregrpcmicroservice.model.SummaryType;

import java.util.Set;

public interface SummaryService {
    Summary get(Long meteoId, Set<MeteoType> meteoTypes, Set<SummaryType> summaryTypes);

    void save(Indicator indicator);

}
