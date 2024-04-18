package ru.fildv.datastoregrpcmicroservice.config;

import lombok.experimental.UtilityClass;

import java.util.Objects;
@UtilityClass
public class KeyHelper {
    private final String defaultPrefix = "app";

    private String prefix = null;

    public void setPrefix(String keyPrefix) {
        prefix = keyPrefix;
    }

    public String getKey(String key) {
        return getPrefix() + ":" + key;
    }

    public String getPrefix() {
        return Objects.requireNonNullElse(prefix, defaultPrefix);
    }
}
