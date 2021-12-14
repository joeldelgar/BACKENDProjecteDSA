package edu.upc.dsa.models;

import org.eclipse.persistence.services.RuntimeServices;

public class Inventory {
    String userName;
    //Real Integers
    int magicBerry;
    //Integers as Boolean, as is not implemented in SQL
    int level1Item;
    int level1Key;
    int level2Item;
    int level2Key;
    int level3Item;
    int level3Key;
    int level4Item;
    int level4Key;
    int level5Item;
    int level5Key;

    public Inventory() {}

    public Inventory(String userName) {
        this.userName = userName;
        this.magicBerry = 0;
        this.level1Item = 0;
        this.level1Key = 0;
        this.level2Item = 0;
        this.level2Key = 0;
        this.level3Item = 0;
        this.level3Key = 0;
        this.level4Item = 0;
        this.level4Key = 0;
        this.level5Item = 0;
        this.level5Key = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMagicBerry() {
        return magicBerry;
    }

    public void setMagicBerry(int magicBerry) {
        this.magicBerry = magicBerry;
    }

    public int getLevel1Item() {
        return level1Item;
    }

    public void setLevel1Item(int level1Item) {
        this.level1Item = level1Item;
    }

    public int getLevel1Key() {
        return level1Key;
    }

    public void setLevel1Key(int level1Key) {
        this.level1Key = level1Key;
    }

    public int getLevel2Item() {
        return level2Item;
    }

    public void setLevel2Item(int level2Item) {
        this.level2Item = level2Item;
    }

    public int getLevel2Key() {
        return level2Key;
    }

    public void setLevel2Key(int level2Key) {
        this.level2Key = level2Key;
    }

    public int getLevel3Item() {
        return level3Item;
    }

    public void setLevel3Item(int level3Item) {
        this.level3Item = level3Item;
    }

    public int getLevel3Key() {
        return level3Key;
    }

    public void setLevel3Key(int level3Key) {
        this.level3Key = level3Key;
    }

    public int getLevel4Item() {
        return level4Item;
    }

    public void setLevel4Item(int level4Item) {
        this.level4Item = level4Item;
    }

    public int getLevel4Key() {
        return level4Key;
    }

    public void setLevel4Key(int level4Key) {
        this.level4Key = level4Key;
    }

    public int getLevel5Item() {
        return level5Item;
    }

    public void setLevel5Item(int level5Item) {
        this.level5Item = level5Item;
    }

    public int getLevel5Key() {
        return level5Key;
    }

    public void setLevel5Key(int level5Key) {
        this.level5Key = level5Key;
    }
}