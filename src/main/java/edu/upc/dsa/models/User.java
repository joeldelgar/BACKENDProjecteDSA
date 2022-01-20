package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

import java.util.ArrayList; //temp
import java.util.List; //temp

public class User {
    String id;
    String name;
    String password;
    String mail;
    int coins;

//    public List<Item> itemList = new ArrayList<>(); //temp
//    public List<User> friendList = new ArrayList<>(); //temp

    public User() {}

    public User(String name, String password, String mail) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.coins = 0;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User{" + "ID='" + id + '\'' +
                ", username='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + mail + '\'' +
                '}';
    }
}
