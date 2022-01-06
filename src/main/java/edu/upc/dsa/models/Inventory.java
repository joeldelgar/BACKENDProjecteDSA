package edu.upc.dsa.models;

public class Inventory {
    String userName;
    String itemName;
    int itemQuantity;
    String itemDescription;
    String itemAvatar;


    public Inventory() {}

    public Inventory(String userName, String itemName, int itemQuantity, String itemDescription, String itemAvatar) {
        this.userName = userName;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemDescription = itemDescription;
        this.itemAvatar = itemAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemAvatar() {
        return itemAvatar;
    }

    public void setItemAvatar(String itemAvatar) {
        this.itemAvatar = itemAvatar;
    }
}