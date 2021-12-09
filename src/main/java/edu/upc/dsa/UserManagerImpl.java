package edu.upc.dsa;

import edu.upc.dsa.DAO.GameSession;
import edu.upc.dsa.DAO.Session;
import edu.upc.dsa.models.CredentialsRegister;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class UserManagerImpl implements UserManager{
    private static UserManager manager;
    protected List<User> userList;
    protected List<Item> itemList;
    protected List<User> onlineUsersList;

    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl(){
        this.userList = new LinkedList<>();
        this.itemList = new LinkedList<>();
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
        String name =user.getName();
        for(User u: this.userList){
            if(u.getName().equals(name)){
                logger.info("User "+name+" Found");
                return null;
            }
        }
        logger.info("New User: "+user.toString());
        this.userList.add(user);
        logger.info("New User Added: "+user);
        return user;
    }

    @Override
    public User addUser(String name, String password, String mail) {
        Session session = null;
        try {
            session = GameSession.openSession();
            User u = new User(name, password, mail);
            session.save(u);
            logger.info("name: " + name + "password: " + password + "mail: " + mail);
        }
        catch (Exception e) {
            logger.error("Error addUser");
        }
        finally {
            session.close();
        }
        return this.addUser(new User(name, password, mail));
    }

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

    @Override
    public User getUser(String name) {
        return null;
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
            logger.info("name: "+ u.getName() + " Password: "+u.getPsw() + " Mail: "+u.getMail());
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
    public User getUserLogin(String name, String psw) {
        logger.info("Nom a buscar: "+name);
        for(User user: this.userList){
            if(user.getName().equals(name) && user.getPsw().equals(psw)){
                logger.info("User "+name+" Found");
                return user;
            }
        }
        logger.info("User "+name+" Not Found");
        return null;
    }

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
    public int userListSize() {
        return this.userList.size();
    }

    @Override
    public void logInUser(String name, String password) {
        User u = this.getUserName(name);
        if (u==null) {
            logger.info("User does not exist");
            //return null;
        }
        else if (u.getPsw().equals(password)){
            this.onlineUsersList.add(u);
            logger.info("User "+name+" logged in successfully");
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

    @Override
    public void logOutUser(String name) {
        User u = this.getUserName(name);
        if (u==null) {
            logger.info("User does not exist");
        }
        else {
            this.onlineUsersList.remove(u);
            logger.info("User "+name+" logged out successfully");
        }
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
    public Item addItem(Item item) {
        logger.info("New Item " + item.getName() +": " + item.getDescription());
        this.itemList.add(item);
        logger.info("New Item Added: " + item);
        return item;
    }

    @Override
    public Item addItem(String name, String description, int value) {
        return this.addItem(new Item(name,description,value));
    }

    @Override
    public Item getItem(String name) {
        for(Item item: this.itemList){
            if(item.getName().equals(name)){
                logger.info("Item "+name+" Found");
                return item;
            }
        }
        logger.info("Item Not Found");
        return null;
    }

    @Override
    public int itemListSize() {
        return this.itemList.size();
    }

    @Override
    public List<Item> getItemListUser(int id) {
        User user = this.getUser(id);
        if(user == null){
            logger.info("Llista d'Items de "+ user.getName());
            List<Item> list = user.getItemList();
            return list;
        }else{
            logger.info("List not Found");
            return null;
        }
    }

    /*
    @Override
    public List<Item> getItemListUser(int userID) {
        Session session = null;
        List<Item> itemList=null;
        try {
            session = GameSession.openSession();
            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("userID", userID);
            itemList = session.findAll(Item.class, params);
        }
        catch (Exception e) {
            logger.error("Error getItemListUser List");
        }
        finally {
            session.close();
        }
        return itemList;
    }
     */

    @Override
    public List<User> getRankingItems(){
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User list1, User list2) {
                return Integer.valueOf(list1.itemList.size()).compareTo(Integer.valueOf(list2.itemList.size()));
            }
        });
        return null;
    }

}
