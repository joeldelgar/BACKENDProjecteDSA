package edu.upc.dsa;

import edu.upc.dsa.models.Enemy;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Level;
import edu.upc.dsa.models.Objecte;

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

    //Get Object data
    public Object getObject(String type);

    //Get Level
    public Level getLevel(int levelId);

    //Add Object to the Object list
    public Objecte addObjectToPlayerList(Objecte object);


}
