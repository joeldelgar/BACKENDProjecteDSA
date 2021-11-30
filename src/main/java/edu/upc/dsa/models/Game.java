package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int gameId;
    private int points;
    private int level;
    private int life;
    private List<Object> objectList = new ArrayList<>();

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

    public List<Object> getObjectList() {
        return objectList;
    }
}
