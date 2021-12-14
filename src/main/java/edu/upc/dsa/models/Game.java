package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String userName;
    private int coins;
    private int points;
    //private int level;
    //private int life;
    //private List<Item> itemList = new ArrayList<>();
    //private List<Enemy> enemyList = new ArrayList<>();
    //private List<Level> levelList = new ArrayList<>();

    public Game() {
    }

    public Game(String userName, int coins, int points) {
        this.userName = userName;
        this.coins = points/10;
        this.points = points;
        //this.level = level;
        //this.life = life;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

/*    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item i){
        itemList.add(i);
    }

    public void addLevel(Level l){
        levelList.add(l);
    }

    public void addEnemy(Enemy e){
        enemyList.add(e);
    }*/
}
