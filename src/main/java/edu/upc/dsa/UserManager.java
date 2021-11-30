package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {

    //Funcions referides només a User
    public User addUser(User user);
    //Afegir Usuari
    public User updateUser(User user, String psw);
    //Modificar Usuari
    public User getUser(String name);
    //Veure Usuari
    public List<User> getAllUsers();
    public void deleteUser(String name);
    //Eliminar Usuari
    public int userListsize();

    //Funcions referides només a Objecte
    public Objecte addObjecte(String name, String description, int value);
    public Objecte addObjecte(Objecte object);
    //Afegir un item
    public Objecte getObjecte(String name);
    public int objectListsize();

    //Login
    public void logUser(String name, String password);

    //Veure Amics
    //public List<User> getFriend(String name);

    public List<Objecte> getObjectListUser(String name);

    public List<User> getRanquingObjectes();

    //Friends
    //public User addFriend(String name);
    //public User deleteFriend(String name);
    public List<User> getFriends(String name);


    public List<User> getLoggedUsers();
}
