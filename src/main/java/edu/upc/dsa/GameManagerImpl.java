package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class GameManagerImpl implements GameManager{

    private static GameManager manager;
    protected List<Game> gameList;

    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private GameManagerImpl(){
        this.gameList= new LinkedList<>();
    }

    //Singleton
    public static GameManager getInstance(){
        if(manager==null){
            manager= new GameManagerImpl();
        }
        return manager;
    }

    @Override
    public Game addGame(Game game) {
        for(Game g: this.gameList){
            if(g.equals(game)){
                logger.info("Game with ID "+ game.getGameId() +"already exists");
                return game;
            }
        }
        gameList.add(game);
        logger.info("Game with ID " + game.getGameId() + "added successfully");
        return game;
    }

    @Override
    public Game getGame(int id) {
        for(Game game: this.gameList){
            if(game.getGameId()==id){
                logger.info("Game with ID "+ id +"found");
                return game;
            }
        }
        logger.info("Game with ID "+ id +" Not Found");
        return null;
    }

    @Override
    public Game deleteGame(int id) {
        for(Game game: this.gameList){
            if(game.getGameId()==id){
                gameList.remove(game);
                logger.info("Game with ID "+ id +"deleted");
                return game;
            }
        }
        logger.info("Game with ID "+ id +" Not Found");
        return null;
    }

    @Override
    public Enemy getEnemy(String type) {
        return null;
    }

    @Override
    public Item getItem(String type) {
        return null;
    }

    @Override
    public Level getLevel(int levelId) {
        return null;
    }

    @Override
    public Item addItemToPlayerList(Item item) {
        return null;

    }

    @Override
    public int gameListSize() {
        return this.gameList.size();
    }

    @Override
    public void addItem(int id, Item i) {
        Game g = this.gameList.get(id);
        g.addItem(i);
    }

    @Override
    public void addEnemy(int id, Enemy e) {
        Game g = this.gameList.get(id);
        g.addEnemy(e);
    }

    @Override
    public void addLevel(int id, Level l) {
        Game g = this.gameList.get(id);
        g.addLevel(l);

    }


}
