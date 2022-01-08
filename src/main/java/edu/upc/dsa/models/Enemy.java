package edu.upc.dsa.models;

public class Enemy {
    private String name;
    private int damage;
    private String description;
    private String avatar;

    public Enemy(){}

    public Enemy(String name, int damage, String description, String avatar) {
        this.name = name;
        this.damage = damage;
        this.description = description;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
