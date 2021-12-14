package edu.upc.dsa.models;

public class Item {
    String name;
    int cost;
    String description;
    String avatar;

    public Item() {}

    public Item(String name, int cost, String description, String avatar) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
