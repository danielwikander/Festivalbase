
// Get timetable for specific scene
SELECT time, band_playing FROM schedule WHERE scene = *variable* SORT BY time ASC;


INSERT INTO bands.contact_person_id WHERE band_id = band.getBandID()
