package ru.fildv.grpcmeteogeneratormicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.fildv.grpcmeteogeneratormicroservice.model.Indicator;
import ru.fildv.grpcmeteogeneratormicroservice.web.dto.IndicatorDto;

@Mapper(componentModel = "spring")
public interface IndicatorMapper extends Mappable<Indicator, IndicatorDto> {
}
