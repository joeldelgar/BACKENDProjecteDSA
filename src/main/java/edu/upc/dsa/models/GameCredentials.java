package edu.upc.dsa.models;

public class GameCredentials {

    String userName;
    int coins;
    int points;

    public GameCredentials(){}

    public GameCredentials(String userName, int coins, int points) {
        this.userName = userName;
        this.coins = coins;
        this.points = points;
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

    @Override
    public String toString() {
        return "CredentialsGame{" +
                "userName='" + userName + '\'' +
                " coins=" + coins +
                ", points=" + points +
                '}';
    }
}