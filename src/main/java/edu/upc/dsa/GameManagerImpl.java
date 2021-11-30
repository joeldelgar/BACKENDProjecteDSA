package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class GameManagerImpl implements GameManager{

    private static GameManager manager;
    protected List<Game> gameList;
    //protected List<Objecte> objectList;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private GameManagerImpl(){
        this.gameList= new LinkedList<>();
        //this.objectList=new LinkedList<>();
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
    public Object getObject(String type) {
        return null;
    }

    @Override
    public Level getLevel(int levelId) {
        return null;
    }

    @Override
    public Objecte addObjectToPlayerList(Objecte object) {
        return null;

    }
}
