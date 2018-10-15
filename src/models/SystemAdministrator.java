package models;

public class SystemAdministrator {

    private String username;
    private String password;

    public SystemAdministrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
