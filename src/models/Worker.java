package models;

/**
 * Represents a worker.
 */
public class Worker {
    private String person_number;
    private String name;
    private String address;

    public Worker(String person_number, String name, String address) {
        this.person_number = person_number;
        this.name          = name;
        this.address       = address;
    }

    public String getPerson_number() {
        return person_number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
