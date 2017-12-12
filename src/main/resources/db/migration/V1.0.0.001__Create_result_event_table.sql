CREATE TABLE result_event (
    id                  VARCHAR(36),
    datetime            TIMESTAMP,
    first_number        INTEGER,
    second_number       INTEGER,
    answer              INTEGER,
    answer_time_millis  INTEGER,
    correct             BOOLEAN,
    point               INTEGER,
    CONSTRAINT result_event_pk PRIMARY KEY(id)
);
