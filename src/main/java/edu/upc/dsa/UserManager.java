package edu.upc.dsa;

import edu.upc.dsa.models.CredentialsRegister;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {

    //Funcions referides només a User

    //Afegir Usuari
    public User addUser(User user);
    public User addUser(String name, String password, String mail);

    //Veure Usuari
    public User getUser(String id);
    //public User getUser(int userID);
    public User getUserName(String name);
    public User getUserLogin (String name, String password);
    public List<User> getAllUsers();
    //public List<User> getAllUsers();

    //Modificar Usuari
    public User updateUser(User user, CredentialsRegister reg);
    //public void updateUser(String newName, String newEmail, String newPassword, int id);

    //Eliminar Usuari
    public void deleteUser(String id);
    //public void deleteUser(int id);
    public int userListSize();

    //Login
    public void logInUser(String name, String password);
    public List<User> getLoggedUsers();
    public void logOutUser(String name);

    //Friends
    //public User addFriend(int id);
    //public User deleteFriend(String name);
    public List<User> getFriends(String id);

    //Funcions referides només a Item
    public Item addItem(Item item);
    public Item addItem(String name, String description, int value);
    //Afegir un item
    public Item getItem(String name);
    public int itemListSize();

    public List<Item> getItemListUser(String id);
    //public List<Item> getItemListUser(int userID);

    public List<User> getRankingItems();

    void clear();
}
