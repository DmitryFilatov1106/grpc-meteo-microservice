package ru.fildv.datastoregrpcmicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.fildv.datastoregrpcmicroservice.model.Summary;
import ru.fildv.datastoregrpcmicroservice.web.dto.SummaryDto;

@Mapper(componentModel = "spring")
public interface SummaryMapper extends Mappable<Summary, SummaryDto> {
}
