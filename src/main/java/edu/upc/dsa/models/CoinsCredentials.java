package edu.upc.dsa.models;

public class CoinsCredentials {

    int coins;
    String userName;

    public CoinsCredentials() {}

    public CoinsCredentials(int coins, String username) {
        this.coins = coins;
        this.userName = username;
    }

    public int getUserCoins() {
        return coins;
    }

    public void setUserCoins(int coins) {
        this.coins = coins;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}