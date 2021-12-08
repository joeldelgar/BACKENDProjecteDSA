package edu.upc.dsa;

import edu.upc.dsa.DAO.GameSession;
import edu.upc.dsa.DAO.Session;
import edu.upc.dsa.models.CredentialsRegister;
import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

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

    public void clear(){
        manager = new UserManagerImpl();
    }

    @Override
    public User addUser(User user) {
        logger.info("New User: "+user.toString());
        this.userList.add(user);
        logger.info("New User Added: "+user);
        return user;
    }

    /*
    @Override
    public int addUser(String name, String mail, String password) {
        Session session = null;
        int userID = 0;
        try {
            session = GameSession.openSession();
            User u = new User(name, mail, password);
            session.save(u);
            logger.info("name: " + name + "mail: " + mail);
        }
        catch (Exception e) {
            logger.error("Error addUser");
        }
        finally {
            session.close();
        }

        return userID;
    }
     */

    @Override
    public User updateUser(User u, CredentialsRegister reg) {
        if (u!=null){
            if (!(u.getPsw().equals(reg.getPassword()))){
                u.setPsw(reg.getPassword());
                logger.info("User "+ u.getName() + " has updated the password");
            }
            else if (!(u.getMail().equals(reg.getMail()))){
                u.setMail(reg.getMail());
                logger.info("User "+ u.getName() + " has updated the mail");
            }
        }else{
            logger.info("User "+u.getName()+" Not Found");
        }
        return u;
    }

    /*
    @Override
    public void updateUser(String newName, String newEmail, String newPassword, int id) {
        User u = this.getUser(id);
        u.setName(newName);
        u.setMail(newEmail);
        u.setPsw(newPassword);

        Session session = null;

        try {
            session = GameSession.openSession();
            session.update(User.class, id);
        }
        catch (Exception e) {
            logger.error("Error updateUser");
        }
        finally {
            session.close();
        }
    }
    */

    @Override
    public User getUser(int id) {
        logger.info("Usuari amb id: "+ id);
        for(User user: this.userList){
            if(user.getId()==id){
                logger.info("User with id "+id+" Found");
                return user;
            }
        }
        logger.info("User with id "+id+" Not Found");
        return null;
    }

    /*
    public User getUser(int userID) {
        Session session = null;
        User u = null;
        try {
            session = GameSession.openSession();
            u = (User)session.get(User.class, userID);
            logger.info("name: "+ u.getName() + " Mail: "+u.getMail() + " Password: "+u.getPsw());
        }
        catch (Exception e) {
            logger.error("Error getUser");
        }
        finally {
            session.close();
        }

        return u;
    }
     */

    @Override
    public User getUserName(String name) {
        logger.info("Usuari: "+ name);
        for(User user: this.userList){
            if(user.getName().equals(name)){
                logger.info("User "+name+" Found");
                return user;
            }
        }
        logger.info("User with id "+name+" Not Found");
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userList;
    }

    /*
    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> userList=null;
        try {
            session = GameSession.openSession();
            userList = session.findAll(User.class);
        }
        catch (Exception e) {
            logger.error("Error getUser List");
        }
        finally {
            session.close();
        }
        return userList;
    }
     */

    @Override
    public void deleteUser(int id) {
        User user= this.getUser(id);
        if(user==null){
            logger.info("User with id "+id+" Not Found");
        }else{
            this.userList.remove(user);
            logger.info("User with id "+id+" Deleted");
        }
    }

    /*
    @Override
    public void deleteUser(int id) {
        User u = this.getUser(id);
        Session session = null;
        try {
            session = GameSession.openSession();
            session.delete(User.class);
        }
        catch (Exception e) {
            logger.error("Error deleteUser");
        }
        finally {
            session.close();
        }
    }
     */

    @Override
    public int userListsize() {
        return this.userList.size();
    }

    @Override
    public void logUser(String name, String password) {
        User u = this.getUserName(name);
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
    public List<User> getLoggedUsers() {
        return onlineUsersList;
    }

    //@Override
    /*public User addFriend(int id) {
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
    public List<User> getFriends(int id) {
        User u = this.getUser(id);
        return u.getFriendList();
    }

    @Override
    public Objecte addObjecte(String name, String description, int value) {
        return this.addObjecte(new Objecte(name,description,value));
    }

    @Override
    public Objecte addObjecte(Objecte object) {
        logger.info("New Object: "+object.toString());
        this.objectList.add(object);
        logger.info("New Object Added: "+object);
        return object;
    }

    @Override
    public Objecte getObjecte(String name) {
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
    public int objectListsize() {
        return this.objectList.size();
    }

    @Override
    public List<Objecte> getObjectListUser(int id) {
        User user = this.getUser(id);
        if(user == null){
            logger.info("Llista d'Objectes de "+ user.getName());
            List<Objecte> list = user.getObjectList();
            return list;
        }else{
            logger.info("List not Found");
            return null;
        }
    }

    /*
    @Override
    public List<Objecte> getObjectListUser(int userID) {
        Session session = null;
        List<Objecte> objecteList=null;
        try {
            session = GameSession.openSession();
            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("userID", userID);
            objecteList = session.findAll(Objecte.class, params);
        }
        catch (Exception e) {
            logger.error("Error getObjectListUser List");
        }
        finally {
            session.close();
        }
        return objecteList;
    }
     */

    @Override
    public List<User> getRanquingObjectes(){
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User list1, User list2) {
                return Integer.valueOf(list1.objectList.size()).compareTo(Integer.valueOf(list2.objectList.size()));
            }
        });
        return null;
    }

}
