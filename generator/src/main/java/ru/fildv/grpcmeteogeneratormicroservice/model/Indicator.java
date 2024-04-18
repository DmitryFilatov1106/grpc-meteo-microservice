package ru.fildv.grpcmeteogeneratormicroservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Indicator {
    private Long meteoId;    // meteo appliance
    private LocalDateTime timestamp;
    private double value;
    private MeteoType meteoType;
}
