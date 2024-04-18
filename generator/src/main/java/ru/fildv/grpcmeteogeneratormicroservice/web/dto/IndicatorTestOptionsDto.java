package ru.fildv.grpcmeteogeneratormicroservice.web.dto;

import ru.fildv.grpcmeteogeneratormicroservice.model.MeteoType;

public record IndicatorTestOptionsDto(int delayInSeconds,
                                      MeteoType[] meteoTypes) {
}
