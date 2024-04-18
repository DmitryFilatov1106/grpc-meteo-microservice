package ru.fildv.grpcmeteogeneratormicroservice.model.test;

import lombok.Getter;
import lombok.Setter;
import ru.fildv.grpcmeteogeneratormicroservice.model.MeteoType;

@Getter
@Setter
public class IndicatorTestOptions {
    private int delayInSeconds;
    private MeteoType[] meteoTypes;
}
