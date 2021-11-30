package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String psw;

    public List<Objecte> objectList = new ArrayList<>();

    public List<User> friendList = new ArrayList<>();

    public User(String name, String psw){
        //this();
        this.psw=psw;
        this.name=name;
    }

    public User() {}

    public List<Objecte> getObjectList(){
        return objectList;
    }

    public List<User> getFriendList() {return friendList;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public User addFriend(User u){
        friendList.add(u);
        return u;
    }

    public User deleteFriend(User u){
        friendList.remove(u);
        return u;

    }

    public void addObjecte(Objecte e){}
    public void deleteObjecte(Objecte e){}
}
