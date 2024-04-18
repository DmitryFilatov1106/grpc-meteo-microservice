package ru.fildv.datastoregrpcmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.fildv.grpcmeteo.GIndicator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Indicator {
    private Long id;
    private Long meteoId;    // meteo appliance
    private LocalDateTime timestamp;
    private double value;
    private MeteoType meteoType;

    public Indicator(GIndicator indicator) {
        this.id = indicator.getId();
        this.meteoId = indicator.getMeteoId();
        this.timestamp = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(
                        indicator.getTimestamp().getSeconds(),
                        indicator.getTimestamp().getNanos()
                ),
                ZoneId.systemDefault()
        );
        this.value = indicator.getValue();
        this.meteoType = MeteoType.valueOf(indicator.getMeteoType().name());
    }
}