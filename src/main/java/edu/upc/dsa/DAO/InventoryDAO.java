package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import io.swagger.models.auth.In;

import java.util.List;

public interface InventoryDAO {

    boolean createInventory(Inventory inventory);

    List<Inventory> getAllByParameter(String parameter, Object value);

    Inventory getInventoryByUserName(String userName);
    Inventory getByParameter(String parameter, Object value);
    Inventory getInventoryByUserNameAndItemName(String userName, String itemName);
    Inventory getByTwoParameters(String firstParameter, Object firstValue, String secondParameter, Object secondValue);
    Object getParameterByParameter(String parameter, String byParameter, Object value);

    boolean existsInventoryByUserNameAndItemName(String userName, String itemName);

    boolean update(Inventory inventory);
    boolean updateByParameter(Inventory inventory, String parameter, Object value);
    boolean updateUserName(String oldName, User newUser);
    boolean updateByTwoParameters(Inventory inventory, String firstParameter, Object firstValue, String secondParameter, Object secondValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue, String byParameter, Object byParameterValue);
    boolean updateParameterByTwoParameters(Class theClass, String parameter, Object parameterValue, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue);
    boolean updateItemQuantityByUserNameAndItemName(int itemQuantity, String userName, String itemName);

    boolean delete(Inventory inventory);
    boolean deleteInventoryByUserName(String userName);
    boolean deleteByParameter(String parameter, Object value);
    boolean deleteByTwoParameters(String firstParameter, Object firstValue, String secondParameter, Object secondValue);
}