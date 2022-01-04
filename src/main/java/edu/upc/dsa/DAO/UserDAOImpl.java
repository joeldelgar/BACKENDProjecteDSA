package edu.upc.dsa.DAO;

import edu.upc.dsa.models.User;

import java.util.LinkedList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private static UserDAO instance;
    private final Session session;

    private UserDAOImpl() {
        session = FactorySession.openSession();
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean addUser(User user) {
        return session.create(user);
    }

    @Override
    public User getUser(String name) {
        return ((User) session.getByParameter(User.class, "name", name));
    }

    @Override
    public boolean getPasswordByName(String name, String password) {
        String passwordUser = (String) session.getParameterByParameter(User.class, "password", "name", name);
        if (password.equals(passwordUser))
            return true;
        else
            return false;
    }

    @Override
    public Object getParameterByParameter(String parameter, String byParameter, Object value) {
        return session.getParameterByParameter(User.class, parameter, byParameter, value);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> userList = null;
        List<String> params= new LinkedList<>();

        try {
            String query = "SELECT * FROM User;";
            session = FactorySession.openSession();
            userList = (List) session.queryObjects(query, User.class, params);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return userList;
    }

    @Override
    public boolean existsName(String name) {
        if (session.getParameterByParameter(User.class, "name", "name", name) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean existsMail(String mail) {
        if (session.getByParameter(User.class, "mail", mail) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean updateUser(User user) {
        return session.update(user);
    }

    @Override
    public boolean updateByParameter(User user, String parameter, Object value) {
        return session.updateByParameter(user, parameter, value);
    }

    @Override
    public boolean updateUserCoinsByUserName(int userCoins, String userName) {
        return session.updateParameterByParameter(User.class, "coins", userCoins, "name", userName);
    }

    @Override
    public boolean updateUserParameters(String oldName, User newUser) {
        if (session.updateParameterByParameter(User.class, "name", newUser.getName(), "name", oldName) &&
                session.updateParameterByParameter(User.class, "password", newUser.getPassword(), "name", newUser.getName()) &&
                session.updateParameterByParameter(User.class, "mail", newUser.getMail(), "name", newUser.getName()))
            return true;
        else
            return false;
    }

    @Override
    public boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue) {
        return session.updateParameterByParameter(User.class, parameter, parameterValue, byParameter, byParameterValue);
    }

    @Override
    public boolean deleteUser(User user) {
        return session.delete(user);
    }

    @Override
    public boolean deleteUserByName(String name) {
        return session.deleteByParameter(User.class, "name", name);
    }

    @Override
    public boolean deleteByParameter(String parameter, Object value) {
        return session.deleteByParameter(User.class, parameter, value);
    }
}