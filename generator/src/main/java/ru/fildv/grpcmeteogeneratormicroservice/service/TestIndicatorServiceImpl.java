package ru.fildv.grpcmeteogeneratormicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fildv.grpcmeteogeneratormicroservice.model.Indicator;
import ru.fildv.grpcmeteogeneratormicroservice.model.MeteoType;
import ru.fildv.grpcmeteogeneratormicroservice.model.test.IndicatorTestOptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class TestIndicatorServiceImpl implements TestIndicatorService {
    private final ScheduledExecutorService executorService
            = Executors.newSingleThreadScheduledExecutor();
    private final GIndicatorService gIndicatorService;

    @Value("${push.batch-size}")
    private int batchSize;

    @Override
    public void sendMessages(final IndicatorTestOptions testOptions) {
        List<Indicator> indicators = new ArrayList<>();
        if (testOptions.getMeteoTypes().length > 0) {
            executorService.scheduleAtFixedRate(() -> {
                Indicator indicator = Indicator.builder()
                        .meteoId((long) getRandomNumber(1, 5))
                        .timestamp(LocalDateTime.now())
//                        .value(getRandomNumber(1, 1))
                        .meteoType(getRandomMeasurement(
                                testOptions.getMeteoTypes()))
                        .build();

                if (indicator.getMeteoType() == MeteoType.TEMPERATURE) {
                    indicator.setValue(getRandomNumber(-60, 60));
                }
                if (indicator.getMeteoType() == MeteoType.HUMIDITY) {
                    indicator.setValue(getRandomNumber(1, 100));
                }
                if (indicator.getMeteoType() == MeteoType.PRESSURE) {
                    indicator.setValue(getRandomNumber(637, 812));
                }

                indicators.add(indicator);
                if (indicators.size() == batchSize) {
                    gIndicatorService.send(indicators);
                    indicators.clear();
                }
            }, 0, testOptions.getDelayInSeconds(), TimeUnit.SECONDS);
        }
    }

    private double getRandomNumber(final int from, final int to) {
        return from + Math.random() * (to - from);
    }

    private MeteoType getRandomMeasurement(final MeteoType[] measurementTypes) {
        int randomTypeId = (int) (Math.random() * measurementTypes.length);
        return measurementTypes[randomTypeId];
    }
}
