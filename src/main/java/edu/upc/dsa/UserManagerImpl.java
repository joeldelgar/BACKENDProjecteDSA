package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UserManagerImpl implements UserManager{
    private static UserManager manager;
    protected List<User> userList;
    protected List<Objecte> objectList;
    protected List<User> onlineUsersList;

    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl(){
        this.userList = new LinkedList<>();
        this.objectList = new LinkedList<>();
        this.onlineUsersList = new LinkedList<>();
    }

    //Singleton
    public static UserManager getInstance(){
        if(manager==null){
            manager= new UserManagerImpl();
        }
        return manager;
    }

    @Override
    public Objecte addObject(String name, String description, int value) {
        return this.addObject(new Objecte(name,description,value));
    }

    @Override
    public User addUser(User user) {
        logger.info("New User: "+user.toString());
        this.userList.add(user);
        logger.info("New User Added: "+user);
        return user;
    }

    @Override
    public Objecte addObject(Objecte object) {
        logger.info("New Object: "+object.toString());
        this.objectList.add(object);
        logger.info("New Object Added: "+object);
        return object;
    }

    @Override
    public User updateUser(User u, String psw) {
        if (u!=null){
            u.setPsw(psw);
            logger.info("User "+ u.getName() + " has updated the password");
        }else{
            logger.info("User "+u.getName()+" Not Found");
        }
        return u;
    }

    @Override
    public User getUser(String name) {
       for(User user: this.userList){
           if(user.getName().equals(name)){
               logger.info("User "+name+" Found");
               return user;
           }
       }
        logger.info("User "+name+" Not Found");
        return null;
    }

    @Override
    public Objecte getObject(String name) {
        for(Objecte object: this.objectList){
            if(object.getName().equals(name)){
                logger.info("Object "+name+" Found");
                return object;
            }
        }
        logger.info("Object Not Found");
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userList;
    }

    @Override
    public void deleteUser(String name) {
        User user= this.getUser(name);
        if(user==null){
            logger.info("User "+name+" Not Found");
        }else{
            this.userList.remove(user);
            logger.info("User "+name+" Deleted");
        }
    }

    @Override
    public int objectListsize() {
        return this.objectList.size();
    }

    @Override
    public void logUser(String name, String password) {
        User u = this.getUser(name);
        if (u==null) {
            logger.info("User does not exist");
            //return null;
        }
        else if (u.getPsw().equals(password)){
            this.onlineUsersList.add(u);
            logger.info("User "+name+" logged successfully");
            //return u;
        }
        else {
            logger.info("Wrong password");
            //return null;
        }
    }

    @Override
    public List<Objecte> getObjectListUser(String name) {
        User user = this.getUser(name);
        if(user == null){
            logger.info("Llista d'Objectes de "+name);
            List<Objecte> list = user.getObjectList();
            return list;
        }else{
            logger.info("List not Found");
            return null;
        }
    }

    @Override
    public int userListsize() {
        return this.userList.size();
    }

    @Override
    public List<User> getRanquingObjectes(){
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User list1, User list2) {
                return Integer.valueOf(list1.objectList.size()).compareTo(Integer.valueOf(list2.objectList.size()));
            }
        });
        return null;
    }


    //@Override
    /*public User addFriend(String name) {
        for(User user: this.userList){
            if(user.getName().equals(name)){
                user.friendList.add(user);
                logger.info("User "+name+" is your friend!");
                return user;
            }
        }
        logger.info("User "+name+" Not Found");
        return null;
    }*/
    /*
    @Override
    public User deleteFriend(String name) {
        for(User user: this.userList){
            if(user.getName().equals(name)){
                userList.remove(user);
                logger.info("User "+name+" has been removed");
                return user;
            }
        }
        logger.info("User "+name+" Not Found");
        return null;
    }
    */
    @Override
    public List<User> getFriends(String name) {
        User u = this.getUser(name);
        return u.getFriendList();
    }

    @Override
    public List<User> getLoggedUsers() {
        return onlineUsersList;
    }


}
