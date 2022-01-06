package edu.upc.dsa.DAO;

import edu.upc.dsa.DAO.FactorySession;
import edu.upc.dsa.DAO.Session;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import io.swagger.models.auth.In;

import java.util.LinkedList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO{

    private static InventoryDAO instance;
    private final Session session;


    private InventoryDAOImpl() {
        session = FactorySession.openSession();
    }

    public static InventoryDAO getInstance() {
        if (instance==null) {
            instance = new InventoryDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean createInventory(Inventory inventory) {
        return session.create(inventory);
    }

    @Override
    public List<Inventory> getAllByParameter(String parameter, Object value) {
        Session session = null;
        List<Inventory> inventoryList = null;

        try{
            List<String> params = new LinkedList<>();
            String query = "SELECT * FROM Inventory WHERE userName= '"+ value +"'";
            session = FactorySession.openSession();
            inventoryList = (List) session.queryObjects(query, Inventory.class, params);
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return inventoryList;
    }

    @Override
    public Inventory getInventoryByUserName(String userName) {
        return ((Inventory) session.getByParameter(Inventory.class, "userName", userName));
    }

    @Override
    public Inventory getByParameter(String parameter, Object value) {
        return ((Inventory) session.getByParameter(Inventory.class, parameter, value));
    }

    @Override
    public Inventory getInventoryByUserNameAndItemName(String userName, String itemName) {
        return ((Inventory) session.getByTwoParameters(Inventory.class, "userName", userName, "itemName", itemName));
    }

    @Override
    public Inventory getByTwoParameters(String firstParameter, Object firstValue, String secondParameter, Object secondValue) {
        return ((Inventory) session.getByTwoParameters(Inventory.class, firstParameter, firstValue, secondParameter, secondValue));
    }

    @Override
    public Object getParameterByParameter(String parameter, String byParameter, Object value) {
        return session.getParameterByParameter(User.class, parameter, byParameter, value);
    }

    @Override
    public boolean existsInventoryByUserNameAndItemName(String userName, String itemName) {
        if (session.getByTwoParameters(Inventory.class, "userName", userName, "itemName", itemName) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean update(Inventory inventory) {
        return session.update(inventory);
    }

    @Override
    public boolean updateByParameter(Inventory inventory, String parameter, Object value) {
        return session.updateByParameter(Inventory.class, parameter, value);
    }

    @Override
    public boolean updateUserName(String oldName, User newUser) {
        return session.updateParameterByParameter(Inventory.class,"userName", newUser.getName(), "userName", oldName);
    }

    @Override
    public boolean updateByTwoParameters(Inventory inventory, String firstParameter, Object firstValue, String secondParameter, Object secondValue) {
        return session.updateByTwoParameters(Inventory.class, firstParameter, firstValue, secondParameter, secondValue);
    }

    @Override
    public boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue) {
        return session.updateParameterByParameter(Inventory.class, parameter, parameterValue, byParameter, byParameterValue);
    }

    @Override
    public boolean updateParameterByTwoParameters(Class theClass, String parameter, Object parameterValue, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue) {
        return session.updateParameterByTwoParameters(Inventory.class, parameter, parameterValue, byFirstParameter, byFirstParameterValue, bySecondParameter, bySecondParameterValue);
    }

    @Override
    public boolean updateItemQuantityByUserNameAndItemName(int itemQuantity, String userName, String itemName) {
        return session.updateParameterByTwoParameters(Inventory.class, "itemQuantity", itemQuantity, "userName", userName, "itemName", itemName);
    }

    @Override
    public boolean delete(Inventory inventory) {
        return session.delete(inventory);
    }

    @Override
    public boolean deleteInventoryByUserName(String userName) {
        return session.deleteByParameter(Inventory.class, "userName", userName);
    }

    @Override
    public boolean deleteByParameter(String parameter, Object value) {
        return session.deleteByParameter(Inventory.class, parameter, value);
    }

    @Override
    public boolean deleteByTwoParameters(String firstParameter, Object firstValue, String secondParameter, Object secondValue) {
        return session.deleteByTwoParameters(Inventory.class, firstParameter, firstValue, secondParameter, secondValue);
    }
}