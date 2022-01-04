package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import io.swagger.models.auth.In;

import java.util.List;

public interface InventoryDAO {

    boolean createInventory(Inventory inventory);

    Inventory getInventoryByUserName(String userName);
    Inventory getByParameter(String parameter, Object value);
    Object getParameterByParameter(String parameter, String byParameter, Object value);

    boolean update(Inventory inventory);
    boolean updateByParameter(Inventory inventory, String parameter, Object value);
    boolean updateItemCountByUserName(String itemName, Object itemCount, String userName);
    boolean updateUserName(String oldName, User newUser);
    boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue);

    boolean delete(Inventory inventory);
    boolean deleteInventoryByUserName(String userName);
    boolean deleteByParameter(String parameter, Object value);
}