package ru.fildv.grpcmeteoanalyzermicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.fildv.grpcmeteo.GIndicator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "indicator")
@NoArgsConstructor
@Getter
@ToString
public class Indicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meteo_id")
    private Long meteoId;    // meteo appliance

    private LocalDateTime timestamp;
    private double value;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private MeteoType meteoType;

    public Indicator(final GIndicator indicator) {
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
