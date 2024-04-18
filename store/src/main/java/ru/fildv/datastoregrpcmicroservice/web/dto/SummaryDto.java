package ru.fildv.datastoregrpcmicroservice.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.fildv.datastoregrpcmicroservice.model.MeteoType;
import ru.fildv.datastoregrpcmicroservice.model.Summary;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SummaryDto {
    private long meteoId;
    private Map<MeteoType, List<Summary.SummaryEntry>> values;
}
