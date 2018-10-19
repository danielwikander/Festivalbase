package models;

/**
 * Represents a band.
 */
public class Band {
    private String band_name;
    private String band_country_of_origin;
    private String band_info;
    private String contact_person_id;

    public Band(String band_name, String band_country_of_origin, String band_info, String contact_person_id) {
        this.band_name = band_name;
        this.band_country_of_origin = band_country_of_origin;
        this.band_info = band_info;
        this.contact_person_id = contact_person_id;
    }

    public Band(String band_name, String band_country_of_origin, String band_info) {
        this.band_name = band_name;
        this.band_country_of_origin = band_country_of_origin;
        this.band_info = band_info;
    }

    public String getBand_name() {
        return band_name;
    }

    public String getBand_info() {
        return band_info;
    }

    public String getContact_person_id() {
        return contact_person_id;
    }

    public String getBand_country_of_origin() {
        return band_country_of_origin;
    }

}
