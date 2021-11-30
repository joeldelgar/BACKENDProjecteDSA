package edu.upc.dsa.models;

public class Credentials {
    private String name;
    private String password;

    public Credentials() {}

    public Credentials(String username, String password) {
        this.name = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
