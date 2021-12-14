package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


public class ItemDAOImpl implements ItemDAO {

    private static ItemDAO instance;
    private final Session session;
    static final Logger logger = Logger.getLogger(ItemDAOImpl.class.getName());

    private ItemDAOImpl() {
        session = FactorySession.openSession();
    }

    public static ItemDAO getInstance() {
        if (instance==null) {
            instance = new ItemDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean create(Item item) {
        return session.create(item);
    }

    @Override
    public List<Item> getAll() {
        Session session = null;
        List<Item> itemList = null;

        try{
            List<String> params= new LinkedList<>();
            String query = "SELECT * FROM Item;";
            session = FactorySession.openSession();
            itemList = (List) session.queryObjects(query, Item.class, params);
            logger.info("itemList: " + itemList);
        }
        catch(Exception e) {
            e.printStackTrace();

        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return itemList;
    }

    @Override
    public Item getItemByName(String name){
        return ((Item) session.getByParameter(Item.class, "name", name));
    }

    @Override
    public Item getByParameter(String parameter, Object value){
        return ((Item) session.getByParameter(Item.class, parameter, value));
    }

    @Override
    public boolean existsItem(String name) {
        if (session.getByParameter(Item.class, "name", name) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean update(Item item) {
        return session.update(item);
    }

    @Override
    public boolean updateByParameter(Item item, String parameter, Object value) {
        return session.updateByParameter(Item.class, parameter, value);
    }

    @Override
    public boolean delete(Item item) {
        return session.delete(item);
    }

    @Override
    public boolean deleteByParameter(String parameter, Object value) {
        return session.deleteByParameter(Item.class, parameter, value);
    }
}