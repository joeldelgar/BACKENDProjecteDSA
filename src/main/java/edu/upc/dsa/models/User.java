package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

import java.util.ArrayList; //temp
import java.util.List; //temp

public class User {
    String id;
    String name;
    String password;
    String mail;
    int health;
    int coins;

//    public List<Item> itemList = new ArrayList<>(); //temp
//    public List<User> friendList = new ArrayList<>(); //temp

    public User() {}

    public User(String name, String password, String mail) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.health = 100;
        this.coins = 10;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    //To review

/*    public List<Item> getItemList(){
        return itemList;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public User addFriend(User u){
        friendList.add(u);
        return u;
    }

    public User deleteFriend(User u){
        friendList.remove(u);
        return u;
    }

    public void addItem(Item e){}
    public void deleteItem(Item e){}*/
}
