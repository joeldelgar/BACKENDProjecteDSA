package edu.upc.dsa.models;

public class GameCredentials {

    String userName;
    int points;
    int health;

    public GameCredentials(){}

    public GameCredentials(String userName, int points, int health) {
        this.userName = userName;
        this.points = points;
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

    @Override
    public String toString() {
        return "CredentialsGame{" +
                "userName='" + userName + '\'' +
                " points=" + points +
                " health=" + health +
                '}';
    }
}