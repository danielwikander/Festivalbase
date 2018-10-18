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
    day                     date    NOT NULL,
    time                    time    NOT NULL,
    band_playing            text    NOT NULL REFERENCES bands,
    scene                   text    NOT NULL REFERENCES scenes,
    PRIMARY KEY (day, time, scene)
);

CREATE TABLE bandmember ( 
    bandmember_id           serial  PRIMARY KEY,
    bandmember_name         text    NOT NULL,
    bandmember_info         text    
);

CREATE TABLE bandmember_association (
    bandmember_id           int     NOT NULL REFERENCES bandmember,
    band                    text    NOT NULL REFERENCES bands
);

CREATE TABLE system_administrators (
    username                text    NOT NULL PRIMARY KEY,
    password                text    NOT NULL
);

CREATE TABLE security_schedule (
    day                     date    NOT NULL,
    time                    time    NOT NULL,
    scene                   text    NOT NULL REFERENCES scenes,
    responsible_worker      text    NOT NULL REFERENCES workers,
    PRIMARY KEY (day, time, responsible_worker)
);