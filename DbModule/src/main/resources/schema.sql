CREATE TABLE IF NOT EXISTS WEATHER(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    LOCATION VARCHAR(50) COMMENT'НАЗВАНИЕ ГОРОДА'
);
COMMENT ON TABLE WEATHER IS 'ПОГОДА';

CREATE TABLE IF NOT EXISTS LOCATION(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    weather_id INTEGER COMMENT'ИДЕНТИФИКАТОР ПОГОДЫ',
    woeid INTEGER COMMENT'ИДЕНТИФИКАТОР YAHOO',
    city VARCHAR(50) COMMENT'ГОРОД',
    region VARCHAR(50) COMMENT'РЕГИОН',
    country VARCHAR(100) COMMENT'СТРАНА',
    lat FLOAT COMMENT'ШИРОТА',
    long FLOAT COMMENT'ДОЛГОТА',
    timezone_id VARCHAR(100) COMMENT'ЧАСОВОЙ ПОЯС'
);
COMMENT ON TABLE LOCATION IS 'ГОРОД';

CREATE TABLE IF NOT EXISTS current_observation(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    weather_id INTEGER COMMENT'ИДЕНТИФИКАТОР ПОГОДЫ',
    pubDate INTEGER COMMENT'ДАТА ПУБЛИКАЦИИ'
);
COMMENT ON TABLE CURRENT_OBSERVATION IS 'ТЕКУЩАЯ ПОГОДА';

CREATE TABLE IF NOT EXISTS WIND(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    obs_id INTEGER COMMENT'ИДЕНТИФИКАТОР ТЕКУЩЕЙ ПОГОДЫ',
    chill INTEGER COMMENT'ТЕМПЕРАТУРА',
    direction INTEGER COMMENT'НАПРАВЛЕНИЕ',
    speed FLOAT COMMENT'СКОРОСТЬ'
);
COMMENT ON TABLE WIND IS 'ВЕТЕР';

CREATE TABLE IF NOT EXISTS atmosphere(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    obs_id INTEGER COMMENT'ИДЕНТИФИКАТОР ТЕКУЩЕЙ ПОГОДЫ',
    humidity INTEGER COMMENT'ВЛАЖНОСТЬ',
    visibility INTEGER COMMENT'ВИДИМОСТЬ',
    pressure FLOAT COMMENT'ДАВЛЕНИЕ'
);
COMMENT ON TABLE ATMOSPHERE IS 'АТМОСФЕРА';

CREATE TABLE IF NOT EXISTS astronomy(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    obs_id INTEGER COMMENT'ИДЕНТИФИКАТОР ТЕКУЩЕЙ ПОГОДЫ',
    sunrise VARCHAR(10) COMMENT'РАССВЕТ',
    sunset VARCHAR(10) COMMENT'ЗАКАТ'
);
COMMENT ON TABLE ASTRONOMY IS 'АСТРОНОМИЯ';

CREATE TABLE IF NOT EXISTS CONDITION(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    obs_id INTEGER COMMENT'ИДЕНТИФИКАТОР ТЕКУЩЕЙ ПОГОДЫ',
    text VARCHAR(50) COMMENT'ОПИСАНИЕ',
    temperature INTEGER COMMENT'ТЕМПЕРАТУРА'
);
COMMENT ON TABLE CONDITION IS 'ОПИСАНИЕ';

CREATE TABLE IF NOT EXISTS FORECAST(
    id SERIAL PRIMARY KEY COMMENT'ИДЕНТИФИКАТОР',
    version INTEGER COMMENT'ВЕРСИЯ',
    weather_id INTEGER COMMENT'ИДЕНТИФИКАТОР ПОГОДЫ',
    day VARCHAR(3) COMMENT'ДЕНЬ НЕДЕЛИ',
    date BIGINT COMMENT'ДАТА',
    low INTEGER COMMENT'МИНИМАЛЬНАЯ ТЕМПЕРАТУРА',
    high INTEGER COMMENT'МКСИМАЛЬНАЯ ТЕМПЕРАТУРА',
    text VARCHAR(50) COMMENT'ОПИСАНИЕ'
);
COMMENT ON TABLE FORECAST IS 'ПРОГНОЗ';

ALTER TABLE LOCATION ADD FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID);
ALTER TABLE CURRENT_OBSERVATION ADD FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID);
ALTER TABLE WIND ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE ASTRONOMY ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE ATMOSPHERE ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE CONDITION ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE FORECAST ADD FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID);

CREATE INDEX IX_WEATHER_LOCATION ON WEATHER(LOCATION);