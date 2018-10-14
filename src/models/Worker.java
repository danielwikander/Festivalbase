package models;

public class Worker {

    private int    person_number;
    private String name;
    private String address;


    public Worker(int person_number, String name, String address) {
        this.person_number = person_number;
        this.name          = name;
        this.address       = address;
    }

    public int getPerson_number() {
        return person_number;
    }

    public void setPerson_number(int person_number) {
        this.person_number = person_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
