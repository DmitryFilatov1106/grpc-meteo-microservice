package ru.fildv.grpcmeteogeneratormicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.fildv.grpcmeteogeneratormicroservice.model.test.IndicatorTestOptions;
import ru.fildv.grpcmeteogeneratormicroservice.web.dto.IndicatorTestOptionsDto;

@Mapper(componentModel = "spring")
public interface IndicatorTestOptionsMapper
        extends Mappable<IndicatorTestOptions, IndicatorTestOptionsDto> {
}
