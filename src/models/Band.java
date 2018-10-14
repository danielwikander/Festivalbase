package models;

/**
 * Represents a band
 */
public class Band {
    private String band_name;
    private String band_counry_of_origin;
    private String band_info;
    private int    contact_person_id;

    public void Band(String band_name, String band_country_of_origin, String band_info, int contact_person_id) {
        this.band_name = band_name;
        this.band_counry_of_origin = band_country_of_origin;
        this.band_info = band_info;
        this.contact_person_id = contact_person_id;
    }


    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }

    public String getBand_info() {
        return band_info;
    }

    public void setBand_info(String band_info) {
        this.band_info = band_info;
    }

    public int getContact_person_id() {
        return contact_person_id;
    }

    public void setContact_person_id(int contact_person_id) {
        this.contact_person_id = contact_person_id;
    }

    public String getBand_counry_of_origin() {
        return band_counry_of_origin;
    }

    public void setBand_counry_of_origin(String band_counry_of_origin) {
        this.band_counry_of_origin = band_counry_of_origin;
    }
}
