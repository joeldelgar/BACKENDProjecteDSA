package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.User;

import java.util.List;

public interface GameDAO {

    boolean addGame(Game game);

    List<Game> getAll();

    List<Game> getAllByParameter(String parameter, Object value);
    Game getGameByUserName(String userName);
    Game getByParameter(String parameter, Object value);

    boolean existsUserName(String userName);

    boolean update(Game game);
    boolean updateByParameter(Game game, String parameter, Object value);
    boolean updateUserName(String oldName, User newUser);
    boolean updatePointsByUserName(int Points, String userName);
    boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue);

    boolean delete(Game game);
    boolean deleteByParameter(String parameter, Object value);
}