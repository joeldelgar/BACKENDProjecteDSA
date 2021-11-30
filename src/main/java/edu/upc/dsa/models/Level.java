package edu.upc.dsa.models;

public class Level {
    private int levelId;
    private String layout;

    public Level() {}

    public Level(int levelId, String layout) {
        this.levelId = levelId;
        this.layout = layout;
    }

    public String getLayout() {
        return layout;
    }
}
