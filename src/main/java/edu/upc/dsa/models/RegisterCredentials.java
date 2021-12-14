package edu.upc.dsa.models;

public class RegisterCredentials {
    String name;
    String password;
    String mail;
    //altres parametres que estaran al registre més endavant
    /*
    String registerData;
    ...
    */

    public RegisterCredentials() {}

    public RegisterCredentials(String name, String password, String mail) {
        this.name = name;
        this.password = password;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
