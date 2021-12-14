package edu.upc.dsa.DAO;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UserDAO {

    boolean addUser(User user);

    User getUser(String name);
    boolean getPasswordByName(String name, String password);
    Object getParameterByParameter(String parameter, String byParameter, Object value);
    List<User> getAllUsers();

    boolean existsName(String name);
    boolean existsMail(String mail);

    boolean updateUser(User user);
    boolean updateByParameter(User user, String parameter, Object value); //needed?
    boolean updateUserCoinsByUserName(int userCoins, String userName);
    boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue);

    boolean deleteUser(User user);
    boolean deleteUserByName(String name); //needed?
    boolean deleteByParameter(String parameter, Object value); //needed?
}