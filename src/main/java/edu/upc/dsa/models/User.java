package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class User {
    String id;
    String name;
    String psw;

    public List<Object> objectList = new ArrayList<>();

    public User() {
        this.id = RandomUtils.getId();
    }

    public User(String name, String psw){
        this();
        this.psw=psw;
        this.name=name;
    }

    public List<Object> getObjectList(){
        return objectList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
