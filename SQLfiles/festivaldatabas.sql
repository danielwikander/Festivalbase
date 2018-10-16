-- Mörtfors Rock Festival

DROP DATABASE IF EXISTS ah4502projekt;
CREATE DATABASE ah4502projekt;
\c ah4502projekt;

CREATE TABLE scenes (
    scene_name              text    PRIMARY KEY,
    max_capacity            int     NOT NULL
);

CREATE TABLE workers (
    person_number           text    PRIMARY KEY,
    name                    text    NOT NULL,
    address                 text    NOT NULL
);

CREATE TABLE bands (
    band_name               text    NOT NULL PRIMARY KEY,
    band_country_of_origin  text    NOT NULL,
    band_info               text,
    contact_person_id       text    REFERENCES workers
);

CREATE TABLE schedule (
    time                    date    NOT NULL,
    band_playing            text    NOT NULL REFERENCES bands,
    scene                   text    NOT NULL REFERENCES scenes,
    PRIMARY KEY (time, scene)
);

CREATE TABLE bandmember ( 
    bandmember_id           serial  PRIMARY KEY,
    bandmember_name         text,
    bandmember_info         text    
);

CREATE TABLE bandmember_association (
    bandmember_id           int     REFERENCES bandmember,
    band                    text    REFERENCES bands
);

CREATE TABLE system_administrators (
    username                text    PRIMARY KEY,
    password                text
);
