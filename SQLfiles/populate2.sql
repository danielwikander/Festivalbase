
-- Workers
INSERT INTO workers VALUES ('1985-10-05', 'Olof Åkesson', 'Storag. 4, Visby');
INSERT INTO workers VALUES ('1986-05-25', 'Helge Åkesson', 'Lillag. 5, Visby');
INSERT INTO workers VALUES ('1969-01-20', 'Greger Flygman', 'Långgatan 6, Stockholm');
INSERT INTO workers VALUES ('1989-04-12', 'Jessica Kransman', 'Flygvägen 2, Ystad');
INSERT INTO workers VALUES ('1990-10-23', 'Lilleman Storboi', 'Stor väg 1, Umeå');
INSERT INTO workers VALUES ('1955-05-13', 'Lycke Fly', 'Litengata 15, Luleå');
INSERT INTO workers VALUES ('1975-02-12', 'Roligtnamn Namnsson', 'Långsöktgatan 8, Byhåla');
INSERT INTO workers VALUES ('', 'Roligtnamn Namnsson', 'Långsöktgatan 8, Byhåla');


-- Bands
INSERT INTO bands VALUES ('AC/DC', 'Australien', 'Rockband från 70-talet!', '1986-05-25');
INSERT INTO bands VALUES ('Slayer', 'Amerika', '80-tals metal!', '1969-01-20');
INSERT INTO bands VALUES ('Metallica', 'Amerika', 'Metalband från 80-talet!', '1955-05-13');
INSERT INTO bands VALUES ('Iron Maiden', 'England', 'Heavy Metal från 70-talet!', '1990-10-23');
INSERT INTO bands VALUES ('Judas Priest', 'England', 'Engelsk Heavy Metal från 70-talet!', '1986-05-25');
INSERT INTO bands VALUES ('Black Sabbath', 'England', 'Rockband från 60-talet!', '1969-01-20');
INSERT INTO bands VALUES ('Led Zeppelin', 'England', 'Rockband från 60-talet!', '1986-05-25');
INSERT INTO bands VALUES ('The Rolling Stones', 'England', 'Rockband från 60-talet!', '1955-05-13');
INSERT INTO bands VALUES ('Pink Floyd', 'England', 'Psych Rock från 60-talet!', '1955-05-13');


-- Scenes
INSERT INTO scenes VALUES ('Mallorca', 2500);
INSERT INTO scenes VALUES ('The Diesel Tent', 150);
INSERT INTO scenes VALUES ('The Forum', 1500);


-- Schedule
INSERT INTO schedule VALUES('2019-05-10', '19:00', 'Slayer', 'The Diesel Tent');
INSERT INTO schedule VALUES('2019-05-11', '22:00', 'Black Sabbath', 'The Forum');
INSERT INTO schedule VALUES('2019-05-12', '21:00', 'Iron Maiden', 'The Diesel Tent');
INSERT INTO schedule VALUES('2019-05-10', '23:00', 'Metallica', 'The Forum');
INSERT INTO schedule VALUES('2019-05-12', '23:30', 'Judas Priest', 'Mallorca');

-- Bandmembers
INSERT INTO bandmember VALUES (DEFAULT, 'Namn Namnssonhehe', 'Information om Namn Namnssonhehe');
INSERT INTO bandmember VALUES (DEFAULT, 'Angus Young', 'Information om Angus Young');
INSERT INTO bandmember VALUES (DEFAULT, 'Chris Slade', 'Information om Chris Slade');
INSERT INTO bandmember VALUES (DEFAULT, 'Stevie Young', 'Information om Stevie Young');
INSERT INTO bandmember VALUES (DEFAULT, 'Axl Rose', 'Information om Axl Rose');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');

INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');
INSERT INTO bandmember VALUES (DEFAULT, 'Bananaman Tjugoett', 'Info om bananaman!');

-- Bandmember associations
INSERT INTO bandmember_association VALUES (1, 'AC/DC');
INSERT INTO bandmember_association VALUES (2, 'AC/DC');
INSERT INTO bandmember_association VALUES (3, 'AC/DC');
INSERT INTO bandmember_association VALUES (4, 'AC/DC');

INSERT INTO bandmember_association (bandmember_id, band)
(SELECT bandmember.bandmember_id FROM bandmember
WHERE bandmember_name = ?), ?;


-- Security Schedule
INSERT INTO security_schedule VALUES ('2019-05-10', '20:00', 'Mallorca', '1969-01-20');
