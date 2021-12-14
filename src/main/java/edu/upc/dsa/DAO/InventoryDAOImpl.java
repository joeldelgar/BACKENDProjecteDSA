package edu.upc.dsa.DAO;

import edu.upc.dsa.DAO.FactorySession;
import edu.upc.dsa.DAO.Session;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.User;
import io.swagger.models.auth.In;

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
    public Inventory getInventoryByUserName(String userName) {
        return ((Inventory) session.getByParameter(Inventory.class, "userName", userName));
    }

    @Override
    public Inventory getByParameter(String parameter, Object value) {
        return ((Inventory) session.getByParameter(Inventory.class, parameter, value));
    }

    @Override
    public Object getParameterByParameter(String parameter, String byParameter, Object value) {
        return session.getParameterByParameter(User.class, parameter, byParameter, value);
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
    public boolean updateItemCountByUserName(String itemName, Object itemCount, String userName) {
        return session.updateParameterByParameter(Inventory.class, itemName, itemCount, "userName", userName);
    }

    @Override
    public boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue) {
        return session.updateParameterByParameter(Inventory.class, parameter, parameterValue, byParameter, byParameterValue);
    }

    @Override
    public boolean delete(Inventory inventory) {
        return session.delete(inventory);
    }

    @Override
    public boolean deleteByParameter(String parameter, Object value) {
        return session.deleteByParameter(Inventory.class, parameter, value);
    }
}