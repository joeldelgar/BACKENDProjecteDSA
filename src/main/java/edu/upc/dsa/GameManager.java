package edu.upc.dsa;

import edu.upc.dsa.models.Enemy;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Level;
import edu.upc.dsa.models.Item;

public interface GameManager {

    //Game
    //Create a new game
    public Game addGame(Game game);
    //Get game data
    public Game getGame(int id);
    //Delete game
    public Game deleteGame(int id);

    //Get Enemy data
    public Enemy getEnemy(String type);

    //Get Item data
    public Item getItem(String type);

    //Get Level
    public Level getLevel(int levelId);

    //Add Object to the Object list
    public Item addItemToPlayerList(Item item);


    public int gameListSize();
    public void addItem(int id, Item i);
    public void addEnemy (int id, Enemy e);
    public void addLevel (int id, Level l);


}
