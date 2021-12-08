package edu.upc.dsa;

import edu.upc.dsa.models.CredentialsRegister;
import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {

    //Funcions referides només a User
    public User addUser(User user);
    //public int addUser(String name, String mail, String password);
    //Afegir Usuari
    public User updateUser(User user, CredentialsRegister reg);
    //public void updateUser(String newName, String newEmail, String newPassword, int id);
    //Modificar Usuari
    public User getUser(int id);
    //public User getUser(int userID);
    public User getUserName(String name);
    //Veure Usuari
    public List<User> getAllUsers();
    //public List<User> getAllUsers();
    //Eliminar Usuari
    public void deleteUser(int id);
    //public void deleteUser(int id);
    public int userListsize();

    //Login
    public void logUser(String name, String password);
    public List<User> getLoggedUsers();

    //Friends
    //public User addFriend(int id);
    //public User deleteFriend(String name);
    public List<User> getFriends(int id);

    //Funcions referides només a Objecte
    public Objecte addObjecte(String name, String description, int value);
    public Objecte addObjecte(Objecte object);
    //Afegir un item
    public Objecte getObjecte(String name);
    public int objectListsize();

    public List<Objecte> getObjectListUser(int id);
    //public List<Objecte> getObjectListUser(int userID);

    public List<User> getRanquingObjectes();

    void clear();
}
