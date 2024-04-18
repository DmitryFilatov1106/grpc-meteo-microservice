package ru.fildv.grpcmeteogeneratormicroservice.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fildv.grpcmeteogeneratormicroservice.model.Indicator;
import ru.fildv.grpcmeteogeneratormicroservice.model.test.IndicatorTestOptions;
import ru.fildv.grpcmeteogeneratormicroservice.service.GIndicatorService;
import ru.fildv.grpcmeteogeneratormicroservice.service.TestIndicatorService;
import ru.fildv.grpcmeteogeneratormicroservice.web.dto.IndicatorDto;
import ru.fildv.grpcmeteogeneratormicroservice.web.dto.IndicatorTestOptionsDto;
import ru.fildv.grpcmeteogeneratormicroservice.web.mapper.IndicatorMapper;
import ru.fildv.grpcmeteogeneratormicroservice.web.mapper.IndicatorTestOptionsMapper;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/indicator")
public class IndicatorController {
    private final GIndicatorService gIndicatorService;
    private final TestIndicatorService testIndicatorService;

    private final IndicatorMapper indicatorMapper;
    private final IndicatorTestOptionsMapper indicatorTestOptionsMapper;

    @PostMapping("/send")
    public void send(final @RequestBody IndicatorDto indicatorDto) {
        Indicator indicator = indicatorMapper.toEntity(indicatorDto);
        log.info("Was get {}", indicator);
        gIndicatorService.send(indicator);
    }

    @PostMapping("/test/send")
    public void testSend(final @RequestBody
                             IndicatorTestOptionsDto testOptionsDto) {
        IndicatorTestOptions testOptions
                = indicatorTestOptionsMapper.toEntity(testOptionsDto);
        testIndicatorService.sendMessages(testOptions);
    }
}
