package edu.upc.dsa.models;

public class Enemy {
    private String type;
    private int damage;

    public Enemy(){}

    public Enemy(String name, int damage) {
        this.type = type;
        this.damage = damage;
    }

    public String getName() {
        return type;
    }

    public void setName(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
