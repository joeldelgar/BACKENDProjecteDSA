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

    private GameManager gameManager;
    private InventoryManager inventoryManager;
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
        //GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(itemList) {};
        return Response.status(200).entity(itemList).build();

    }

    //Buy an Item
    @POST
    @ApiOperation(value = "Buy an Item", notes = "Item and userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 402, message = "Not enough coins"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Item not found"),
            @ApiResponse(code = 406, message = "Item is already in Inventory")
    })
    @Path("/buyItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(StoreCredentials sCr) {

        String itemName = sCr.getItemName();
        String userName = sCr.getUserName();

        if (userDAO.existsName(userName)) {
            if (itemDAO.existsItem(itemName)) {
                Item item = itemDAO.getItemByName(itemName);
                User user = userDAO.getUser(userName);
                Inventory inventory = inventoryDAO.getInventoryByUserName(userName);

                if (item.getCost() > user.getCoins()) {
                    return Response.status(402).build();
                } else {
                    if (itemName.equals("magicBerry")) {
                        int itemCount = inventory.getMagicBerry() + 1;
                        inventoryDAO.updateItemCountByUserName(itemName, itemCount, userName);
                    } else {
                        int itemState = 0;
                        if (itemName.equals("level1Item")) {
                            if (inventory.getLevel1Item() == 0) {
                               itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level1IKey")) {
                            if (inventory.getLevel1Key() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level2Item")) {
                            if (inventory.getLevel2Item() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level2Key")) {
                            if (inventory.getLevel2Key() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level3Item")) {
                            if (inventory.getLevel3Item() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level3Key")) {
                            if (inventory.getLevel3Key() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level4Item")) {
                            if (inventory.getLevel4Item() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level4Key")) {
                            if (inventory.getLevel4Key() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level5Item")) {
                            if (inventory.getLevel5Item() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        if (itemName.equals("level5Key")) {
                            if (inventory.getLevel5Key() == 0) {
                                itemState = 1;
                            } else
                                return Response.status(406).build();
                        }
                        inventoryDAO.updateItemCountByUserName(itemName, itemState, userName);
                    }
                    int userCoins = user.getCoins() - item.getCost();
                    userDAO.updateUserCoinsByUserName(userCoins, userName);
                    return Response.status(200).entity(sCr).build();
                }
            } else
                return Response.status(405).build();
        } else
            return Response.status(404).build();
    }

    //Get User Inventory
    @GET
    @ApiOperation(value = "Get the Inventory of a User", notes = "userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Inventory.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUserInventory/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInventory(@PathParam("userName") String userName) {

        Inventory inventory = inventoryDAO.getInventoryByUserName(userName);

        if (inventory == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<Inventory> entity = new GenericEntity<Inventory>(inventory) {};
            return Response.status(200).entity(entity).build();
        }
    }

    //Use an Item
    @POST
    @ApiOperation(value = "Use an Item", notes = "Item and userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 402, message = "No units left"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Item not found"),
            @ApiResponse(code = 406, message = "Item can not be used")
    })
    @Path("/useItem")
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
                    if (inventory.getMagicBerry() == 0) {
                        return Response.status(402).build();
                    } else {
                        int op = inventory.getMagicBerry() - 1;
                        inventoryDAO.updateItemCountByUserName(itemName, op, userName);
                        return Response.status(200).build();
                    }
                } else
                    return Response.status(406).build();
            } else
                return Response.status(405).build();
        } else
            return Response.status(404).build();
    }
}