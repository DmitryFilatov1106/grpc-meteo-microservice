CREATE TABLE indicator
(
    id        BIGSERIAL PRIMARY KEY,
    meteo_id  BIGINT    NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    value     FLOAT     NOT NULL,
    type      VARCHAR   NOT NULL
);