package ru.fildv.datastoregrpcmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fildv.datastoregrpcmicroservice.model.Indicator;
import ru.fildv.datastoregrpcmicroservice.model.MeteoType;
import ru.fildv.datastoregrpcmicroservice.model.Summary;
import ru.fildv.datastoregrpcmicroservice.model.SummaryType;
import ru.fildv.datastoregrpcmicroservice.model.exception.IndicatorNotFoundException;
import ru.fildv.datastoregrpcmicroservice.repository.SummaryRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(final Long meteoId,
                       final Set<MeteoType> meteoTypes,
                       final Set<SummaryType> summaryTypes) {
        return summaryRepository.findByMeteoId(
                        meteoId,
                        meteoTypes == null ? Set.of(MeteoType.values())
                                : meteoTypes,
                        summaryTypes == null ? Set.of(SummaryType.values())
                                : summaryTypes
                )
                .orElseThrow(IndicatorNotFoundException::new);
    }

    @Override
    public void save(final Indicator indicator) {
        summaryRepository.save(indicator);
    }
}
