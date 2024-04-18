package ru.fildv.grpcmeteogeneratormicroservice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.fildv.grpcmeteogeneratormicroservice.model.MeteoType;

import java.time.LocalDateTime;

public record IndicatorDto(Long meteoId,
                           @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp,
                           double value,
                           MeteoType meteoType) {
}
