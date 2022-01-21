package edu.upc.dsa.DAO;

import edu.upc.dsa.DAO.FactorySession;
import edu.upc.dsa.DAO.Session;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameDAOImpl implements GameDAO {

    private static GameDAO instance;
    private final Session session;

    private GameDAOImpl() {
        session = FactorySession.openSession();
    }

    public static GameDAO getInstance() {
        if (instance==null) {
            instance = new GameDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean addGame(Game game) {
        return session.create(game);
    }

    @Override
    public List<Game> getAll() {
        Session session = null;
        List<Game> gameList = null;

        try{
            List<String> params= new LinkedList<>();
            String query = "SELECT * FROM Game;";
            session = FactorySession.openSession();
            gameList = (List) session.queryObjects(query, Game.class, params);
        } catch(Exception e) {
            e.printStackTrace();

        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return gameList;
    }

    @Override
    public List<Game> getAllByParameter(String parameter, Object value) {
        Session session = null;
        List<Game> gameList = null;

        try{
            List<String> params= new LinkedList<>();
            String query = "SELECT * FROM Game WHERE userName= '"+ value +"'";
            session = FactorySession.openSession();
            gameList = (List) session.queryObjects(query, Game.class, params);
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return gameList;
    }

    @Override
    public List<Game> orderByParameter(String parameter) {
        Session session = null;
        List<Game> gameList = null;

        try{
            List<String> params= new LinkedList<>();
            String query = "SELECT * FROM Game ORDER BY " + parameter + " DESC";
            session = FactorySession.openSession();
            gameList = (List) session.queryObjects(query, Game.class, params);
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return gameList;
    }

    @Override
    public Game getGameByUserName(String userName) {
        return ((Game) session.getByParameter(Game.class, "userName", userName));
    }

    @Override
    public Game getByParameter(String parameter, Object value) {
        return ((Game) session.getByParameter(Game.class, parameter, value));
    }

    @Override
    public boolean existsUserName(String userName) {
        if (session.getParameterByParameter(Game.class, "userName", "userName", userName) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean update(Game game) {
        return session.update(game);
    }

    @Override
    public boolean updateByParameter(Game game, String parameter, Object value) {
        return session.updateByParameter(game, parameter, value);
    }

    @Override
    public boolean updateUserName(String oldName, User newUser) {
        return session.updateParameterByParameter(Game.class,"userName", newUser.getName(), "userName", oldName);
    }

    @Override
    public boolean updatePointsByUserName(int points, String userName) {
        return session.updateParameterByParameter(Game.class, "points", points, "userName", userName);
    }

    @Override
    public boolean updateHealthByUserName(int health, String userName) {
        return session.updateParameterByParameter(Game.class, "health", health, "userName", userName);
    }

    @Override
    public boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue) {
        return session.updateParameterByParameter(Game.class, parameter, parameterValue, byParameter, byParameterValue);
    }

    @Override
    public boolean delete(Game game) {
        return session.delete(game);
    }

    @Override
    public boolean deleteByParameter(String parameter, Object value) {
        return session.deleteByParameter(Game.class, parameter, value);
    }
}