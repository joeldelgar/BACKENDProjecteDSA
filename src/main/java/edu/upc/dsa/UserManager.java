package edu.upc.dsa;

import edu.upc.dsa.models.Object;
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

    //Funcions referides només a Object
    public Object addObject(String name, String description);
    public Object addObject(Object object);
    //Afegir un item
    public Object getObject(String name);
    public int objectListsize();

    public List<Object> getObjectListUser(String name);

}
