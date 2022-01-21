package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String userName;
    private int points;

    //private int level;
    private int health;
    //private List<Item> itemList = new ArrayList<>();
    //private List<Enemy> enemyList = new ArrayList<>();
    //private List<Level> levelList = new ArrayList<>();

    public Game() {
    }

    public Game(String userName, int points, int health) {
        this.userName = userName;
        this.points = points;
        //this.level = level;
        this.health = health;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
