
// Get timetable for specific scene
SELECT time, band_playing FROM schedule WHERE scene = *variable* SORT BY time ASC;


INSERT INTO bands.contact_person_id WHERE band_id = band.getBandID()



SELECT security_schedule.day, security_schedule.time, security_schedule.scene, security_schedule.responsible_worker, workers.name
FROM security_schedule
INNER JOIN workers ON workers.person_number = security_schedule.responsible_worker;

§
SELECT w.person_number, w.name, COUNT (ba.bandmember_id)
FROM workers w
INNER JOIN bands b ON w.person_number = b.contact_person_id
INNER JOIN bandmember_association ba ON b.band_name = ba.band;

SELECT w.person_number, w.name, COUNT (ba.bandmember_id) AS contact_connections
FROM workers w
INNER JOIN bands b ON w.person_number = b.contact_person_id
INNER JOIN bandmember_association ba ON b.band_name = ba.band
GROUP BY workers
