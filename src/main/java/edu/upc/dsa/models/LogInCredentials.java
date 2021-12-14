package edu.upc.dsa.models;

public class LogInCredentials {
    private String name;
    private String password;

    public LogInCredentials() {}

    public LogInCredentials(String name, String password) {
        this.name = name;
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
