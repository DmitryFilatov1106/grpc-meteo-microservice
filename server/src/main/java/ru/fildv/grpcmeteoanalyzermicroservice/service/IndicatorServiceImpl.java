package ru.fildv.grpcmeteoanalyzermicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fildv.grpcmeteoanalyzermicroservice.model.Indicator;
import ru.fildv.grpcmeteoanalyzermicroservice.repository.IndicatorRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IndicatorServiceImpl implements IndicatorService {
    private final IndicatorRepository indicatorRepository;

    @Override
    public void save(final Indicator indicator) {
        log.info("Data object {} was saved", indicator);
        indicatorRepository.save(indicator);
    }

    @Override
    @Transactional
    public List<Indicator> get(final long batchSize) {
        List<Indicator> indicators
                = indicatorRepository.findAllWithOffset(batchSize);
        if (indicators.size() > 0) {
            indicatorRepository.incrementOffset(
                    Long.min(batchSize, indicators.size()));
        }
        return indicators;
    }
}
