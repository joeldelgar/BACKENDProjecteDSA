package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {

    //Funcions referides només a User
    public User addUser(String name, String psw);
    public User addUser(User user);
    //Afegir Usuari
    public User updateUser(User user);
    //Modificar Usuari
    public User getUser(String name);
    //Veure Usuari
    public List<User> getAllUsers();
    public void deleteUser(String name);
    //Eliminar Usuari
    public int userListsize();

    //Funcions referides només a Objecte
    public Objecte addObject(String name, String description);
    public Objecte addObject(Objecte object);
    //Afegir un item
    public Objecte getObject(String name);
    public int objectListsize();

    //Login
    public void logUser(String name, String password);

    public List<Objecte> getObjectListUser(String name);

}
