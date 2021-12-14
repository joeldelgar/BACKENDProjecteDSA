package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Game;

import java.util.List;

public interface GameDAO {

    boolean addGame(Game game);

    List<Game> getAll();

    List<Game> getAllByParameter(String parameter, Object value);
    Game getByParameter(String parameter, Object value);

    boolean update(Game game);
    boolean updateByParameter(Game game, String parameter, Object value);
    boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue);

    boolean delete(Game game);
    boolean deleteByParameter(String parameter, Object value);
}