CREATE TABLE IF NOT EXISTS WEATHER(
id SERIAL PRIMARY KEY,
version INTEGER,
LOCATION VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS LOCATION(
id SERIAL PRIMARY KEY,
version INTEGER,
weather_id INTEGER,
woeid INTEGER,
city VARCHAR(50),
country VARCHAR(100),
lat FLOAT,
long FLOAT,
timezone_id VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS current_observation(
id SERIAL PRIMARY KEY,
version INTEGER,
weather_id INTEGER,
pubDate INTEGER
);

CREATE TABLE IF NOT EXISTS WIND(
id SERIAL PRIMARY KEY,
version INTEGER,
obs_id INTEGER,
chill INTEGER,
direction INTEGER,
speed FLOAT
);

CREATE TABLE IF NOT EXISTS atmosphere(
id SERIAL PRIMARY KEY,
version INTEGER,
obs_id INTEGER,
humidity INTEGER,
visibility INTEGER,
pressure FLOAT
);

CREATE TABLE IF NOT EXISTS astronomy(
id SERIAL PRIMARY KEY,
version INTEGER,
obs_id INTEGER,
sunrise VARCHAR(10),
sunset VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS CONDITION(
id SERIAL PRIMARY KEY,
version INTEGER,
obs_id INTEGER,
text VARCHAR(50),
code INTEGER,
temperature INTEGER
);

CREATE TABLE IF NOT EXISTS FORECAST(
id SERIAL PRIMARY KEY,
version INTEGER,
weather_id INTEGER,
day VARCHAR(3),
date BIGINT,
low INTEGER,
high INTEGER,
text VARCHAR(50),
code INTEGER
);

ALTER TABLE LOCATION ADD FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID);
ALTER TABLE CURRENT_OBSERVATION ADD FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID);
ALTER TABLE WIND ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE ASTRONOMY ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE ATMOSPHERE ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE CONDITION ADD FOREIGN KEY (OBS_ID) REFERENCES CURRENT_OBSERVATION(ID);
ALTER TABLE FORECAST ADD FOREIGN KEY (WEATHER_ID) REFERENCES WEATHER(ID);

CREATE INDEX IX_WEATHER_LOCATION ON WEATHER(LOCATION);