package ru.fildv.datastoregrpcmicroservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fildv.datastoregrpcmicroservice.model.MeteoType;
import ru.fildv.datastoregrpcmicroservice.model.Summary;
import ru.fildv.datastoregrpcmicroservice.model.SummaryType;
import ru.fildv.datastoregrpcmicroservice.service.SummaryService;
import ru.fildv.datastoregrpcmicroservice.web.dto.SummaryDto;
import ru.fildv.datastoregrpcmicroservice.web.mapper.SummaryMapper;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final SummaryService summaryService;
    private final SummaryMapper summaryMapper;

    @GetMapping("/summary/{meteoId}")
    public SummaryDto getSummary(
            final @PathVariable long meteoId,
            final @RequestParam(value = "mt", required = false)
            Set<MeteoType> meteoTypes,
            final @RequestParam(value = "st", required = false)
            Set<SummaryType> summaryTypes
    ) {
        Summary summary = summaryService.get(meteoId, meteoTypes, summaryTypes);
        return summaryMapper.toDto(summary);
    }
}
