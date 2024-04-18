package ru.fildv.datastoregrpcmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Summary {
    private long meteoId;
    private Map<MeteoType, List<SummaryEntry>> values = new HashMap<>();

    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class SummaryEntry {
        private SummaryType type;
        private double value;
        private long counter;
    }

    public void addValue(final MeteoType type, final SummaryEntry value) {
        if (values.containsKey(type)) {
            List<SummaryEntry> entries = new ArrayList<>(values.get(type));
            entries.add(value);
            values.put(type, entries);
        } else {
            values.put(type, List.of(value));
        }
    }
}
