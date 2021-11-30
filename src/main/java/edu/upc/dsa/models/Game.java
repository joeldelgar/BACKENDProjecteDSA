package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int gameId;
    private int points;
    private int level;
    private int life;
    private List<Objecte> objectList = new ArrayList<>();
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Level> levelList = new ArrayList<>();

    public Game() {
    }

    public Game(int gameId, int points, int level, int life) {
        this.gameId = gameId;
        this.points = points;
        this.level = level;
        this.life = life;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
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

    public List<Objecte> getObjectList() {
        return objectList;
    }

    public void addObjecte(Objecte o){
        objectList.add(o);
    }

    public void addLevel(Level l){
        levelList.add(l);
    }

    public void addEnemy(Enemy e){
        enemyList.add(e);
    }
}
