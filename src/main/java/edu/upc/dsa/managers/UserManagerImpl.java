package edu.upc.dsa.managers;

import org.apache.log4j.Logger;

public class UserManagerImpl implements UserManager{

/*    protected List<User> userList;
    protected List<Item> itemList;
    protected List<User> onlineUsersList;*/

    private static UserManager userManager;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl(){
/*        this.userList = new LinkedList<>();
        this.itemList = new LinkedList<>();
        this.onlineUsersList = new LinkedList<>();*/
    }

    //Singleton
    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManagerImpl();
        }
        return userManager;
    }

    public void clear(){
        userManager = new UserManagerImpl();
    }

/*    @Override
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
    }*/

/*    @Override
    public User getUser(String name) {
        logger.info("User: "+ name);
        for(User user: this.userList){
            if(user.getName().equals(name)){
                logger.info("User "+name+" Found");
                return user;
            }
        }
        logger.info("User "+name+" Not Found");
        return null;
    }*/

/*    @Override
    public List<User> getAllUsers() {
        return this.userList;
    }*/
/*
    @Override
    public int userListSize() {
        return this.userList.size();
    }*/
/*
    @Override
    public User updateUser(User u, CredentialsLogIn reg) {
        if (u!=null){
            if (!(u.getPassword().equals(reg.getPassword()))){
                u.setPassword(reg.getPassword());
                logger.info("User "+ u.getName() + " has updated the password");
            }
            else if (!(u.getName().equals(reg.getName()))){
                u.setName(reg.getName());
                logger.info("User "+ u.getName() + " has updated the user name");
            }
        }else{
            logger.info("User "+u.getName()+" Not Found");
        }
        return u;
    }*/

/*    @Override
    public void deleteUser(String name) {
        User user = this.getUserName(name);
        if(user==null){
            logger.info("User: "+name+" Not Found");
        }else{
            this.userList.remove(user);
            logger.info("User: "+name+" Deleted");
        }
    }*/

/*    @Override
    public void logInUser(String name, String password) {
        User u = this.getUserName(name);
        if (u==null) {
            logger.info("User does not exist");
            //return null;
        }
        else if (u.getPassword().equals(password)){
            this.onlineUsersList.add(u);
            logger.info("User "+name+" logged in successfully");
            //return u;
        }
        else {
            logger.info("Wrong password");
            //return null;
        }
    }*/

/*    @Override
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
    }*/

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
/*    @Override
    public List<User> getFriends(String id) {
        User u = this.getUser(id);
        return u.getFriendList();
    }*/

/*    @Override
    public Item addItem(Item item) {
        logger.info("New Item " + item.getName() +": " + item.getDescription());
        this.itemList.add(item);
        logger.info("New Item Added: " + item);
        return item;
    }*/

/*    @Override
    public Item addItem(String name, String description, int value) {
        return this.addItem(new Item(name,description,value));
    }*/

/*    @Override
    public Item getItem(String name) {
        for(Item item: this.itemList){
            if(item.getName().equals(name)){
                logger.info("Item "+name+" Found");
                return item;
            }
        }
        logger.info("Item Not Found");
        return null;
    }*/

/*    @Override
    public int itemListSize() {
        return this.itemList.size();
    }

    @Override
    public List<Item> getItemListUser(String id) {
        User user = this.getUser(id);
        if(user == null){
            logger.info("Llista d'Items de "+ user.getName());
            List<Item> list = user.getItemList();
            return list;
        }else{
            logger.info("List not Found");
            return null;
        }
    }*/

    /*
    @Override
    public List<Item> getItemListUser(int userID) {
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

/*    @Override
    public List<User> getRankingItems(){
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User list1, User list2) {
                return Integer.valueOf(list1.itemList.size()).compareTo(Integer.valueOf(list2.itemList.size()));
            }
        });
        return null;
    }*/

}
