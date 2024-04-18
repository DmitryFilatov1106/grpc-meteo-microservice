package ru.fildv.datastoregrpcmicroservice.config;

import lombok.experimental.UtilityClass;
import ru.fildv.datastoregrpcmicroservice.model.MeteoType;
@UtilityClass
public class RedisSchema {
    //set
    public String meteoKeys() {
        return KeyHelper.getKey("meteo");
    }

    //hash with summary types
    public String summaryKey(long meteoId, MeteoType meteoType) {
        return KeyHelper.getKey("meteo:" + meteoId + ":" + meteoType.name().toLowerCase());
    }
}
