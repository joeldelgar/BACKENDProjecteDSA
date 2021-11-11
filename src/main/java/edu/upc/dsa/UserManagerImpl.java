package edu.upc.dsa;

import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.List;

public class UserManagerImpl implements UserManager{
    private static UserManager manager;
    protected List<User> userList;
    protected List<Object> objectList;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl(){
        this.userList= new LinkedList<>();
        this.objectList=new LinkedList<>();
    }

    //Singleton
    public static UserManager getInstance(){
        if(manager==null){
            manager= new UserManagerImpl();
        }
        return manager;
    }


    @Override
    public User addUser(String name, String psw) {
       return this.addUser(new User(name,psw));
    }

    @Override
    public Object addObject(String name, String description) {
        return this.addObject(new Object(name,description));
    }

    @Override
    public User addUser(User user) {
        logger.info("New User: "+user.toString());
        this.userList.add(user);
        logger.info("New User Added: "+user);
        return user;
    }

    @Override
    public Object addObject(Object object) {
        logger.info("New Object: "+object.toString());
        this.objectList.add(object);
        logger.info("New Object Added: "+object);
        return object;
    }

    @Override
    public User updateUser(User u) {
        User user = this.getUser(u.getName());
        if(u!=null){
            logger.info("User to Update: "+u.getName());
            user.setName(u.getName());
            user.setPsw(u.getPsw());
            logger.info("User Updated: "+user.getName());
        }else{
            logger.info("User "+u.getName()+" Not Found");
        }
        return user;
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
    public Object getObject(String name) {
        for(Object object: this.objectList){
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
    public List<Object> getAllObjects() {
        return this.objectList;
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
    public void deleteObject(String name) {
        Object object = this.getObject(name);
        if(object==null){
            logger.info("Object Not Found");
        }else{
            this.objectList.remove(object);
            logger.info("Object "+object+" Deleted");
        }
    }

    @Override
    public int objectListsize() {
        return this.objectList.size();
    }


    @Override
    public int userListsize() {
        return this.userList.size();
    }

}
