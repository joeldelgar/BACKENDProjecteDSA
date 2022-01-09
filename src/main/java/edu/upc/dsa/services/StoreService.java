package edu.upc.dsa.services;
import edu.upc.dsa.DAO.*;
import edu.upc.dsa.managers.GameManager;
import edu.upc.dsa.managers.GameManagerImpl;

import edu.upc.dsa.managers.InventoryManager;
import edu.upc.dsa.models.StoreCredentials;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/store", description = "StoreService")
@Path("/store")
public class StoreService {

    //private GameManager gameManager;
    //private InventoryManager inventoryManager;
    private final ItemDAO itemDAO;
    private final UserDAO userDAO;
    private final InventoryDAO inventoryDAO;

    public StoreService() {
        //this.gameManager = GameManagerImpl.getInstance();
        itemDAO = ItemDAOImpl.getInstance();
        inventoryDAO = InventoryDAOImpl.getInstance();
        userDAO = UserDAOImpl.getInstance();
    }

    //Get Store Items
    @GET
    @ApiOperation(value = "Get Store Items", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/itemList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {

        List<Item> itemList = itemDAO.getAll();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(itemList) {};
        return Response.status(200).entity(entity).build();
    }

    //Buy an Item
    @PUT
    @ApiOperation(value = "Buy an Item", notes = "Item and userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 402, message = "Not enough coins"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Item not found"),
            @ApiResponse(code = 406, message = "Item is already in Inventory")
    })
    @Path("/buyItem/{itemName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(StoreCredentials sCr) {

        String itemName = sCr.getItemName();
        String userName = sCr.getUserName();

        if (userDAO.existsName(userName)) {
            if (itemDAO.existsItem(itemName)) {
                Item item = itemDAO.getItemByName(itemName);
                User user = userDAO.getUser(userName);

                Inventory newInventory = new Inventory(userName, itemName, 1, item.getDescription(), item.getAvatar());

                if (item.getCost() > user.getCoins()) {
                    return Response.status(402).build();
                } else {
                    if (itemName.equals("magicBerry")) {
                        Inventory defaultInventory = inventoryDAO.getInventoryByUserNameAndItemName(userName, itemName);
                        int itemQuantity = defaultInventory.getItemQuantity() + 1;
                        inventoryDAO.updateItemQuantityByUserNameAndItemName(itemQuantity, userName, itemName);
                    } else {
                        if (!inventoryDAO.existsInventoryByUserNameAndItemName(userName, itemName))
                            inventoryDAO.createInventory(newInventory);
                        else
                            return Response.status(406).build();
                    }
                    int userCoins = user.getCoins() - item.getCost();
                    userDAO.updateUserCoinsByUserName(userCoins, userName);
                    return Response.status(200).entity(item).build();
                }
            } else
                return Response.status(405).build();
        } else
            return Response.status(404).build();
    }

    //Get User inventoryList
    @GET
    @ApiOperation(value = "Get the Inventory List of a User", notes = "userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Inventory.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/userInventoryList/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInventoryList(@PathParam("userName") String userName) {

        if (userDAO.existsName(userName)) {
            List<Inventory> inventoryList = null;
            try {
                inventoryList = inventoryDAO.getAllByParameter("userName", userName);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            GenericEntity<List<Inventory>> entity = new GenericEntity<List<Inventory>>(inventoryList) {};
            return Response.status(200).entity(entity).build();
        }
        else {
            return Response.status(404).build();
        }
    }


    //Use an Item
    @PUT
    @ApiOperation(value = "Use an Item", notes = "Item and userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 402, message = "No units left"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Item not found"),
            @ApiResponse(code = 406, message = "Item not available")
    })
    @Path("/useItem/{itemName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response useItem(StoreCredentials sCr) {

        String itemName = sCr.getItemName();
        String userName = sCr.getUserName();

        if (userDAO.existsName(userName)) {
            if (itemDAO.existsItem(itemName)) {
                Item item = itemDAO.getItemByName(itemName);
                User user = userDAO.getUser(userName);
                Inventory inventory = inventoryDAO.getInventoryByUserName(userName);

                if (itemName.equals("magicBerry")) {
                    if (inventory.getItemQuantity() == 0) {
                        return Response.status(402).build();
                    } else {
                        int itemQuantity = inventory.getItemQuantity() - 1;
                        inventoryDAO.updateItemQuantityByUserNameAndItemName(itemQuantity, userName, itemName);
                        return Response.status(200).entity(item).build();
                    }
                } else {
                    if (inventoryDAO.existsInventoryByUserNameAndItemName(userName, itemName))
                        return Response.status(200).entity(item).build();
                    else
                        return Response.status(406).build();
                }
            } else
                return Response.status(405).build();
        } else
            return Response.status(404).build();
    }
}