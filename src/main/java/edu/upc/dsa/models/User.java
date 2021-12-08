package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String psw;
    String mail;
    int id;

    public List<Objecte> objectList = new ArrayList<>();

    public List<User> friendList = new ArrayList<>();

    public User(String name, String psw){
        //this();
        this.psw=psw;
        this.name=name;
    }
    /*
    public User(String name, String mail, String psw){
        this.name=name;
        this.mail=mail;
        this.psw=psw;
        //this.id = RandomStringUtils.randomAlphanumeric(8);
    }
     */

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User addFriend(User u){
        friendList.add(u);
        return u;
    }

    public User deleteFriend(User u){
        friendList.remove(u);
        return u;

    }

    //S'agafa el valor de la taula quan s'afegeix a la base de dades
    public void setId(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }



    public void addObjecte(Objecte e){}
    public void deleteObjecte(Objecte e){}
}
