package edu.upc.dsa.models;

public class StoreCredentials {

    String itemName;
    String userName;

    public StoreCredentials() {}

    public StoreCredentials(String itemName, String username) {
        this.itemName = itemName;
        this.userName = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}