package edu.upc.dsa.services;
import edu.upc.dsa.DAO.*;
import edu.upc.dsa.managers.UserManager;
import edu.upc.dsa.managers.UserManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.*;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/user", description = "UserService")
@Path("/user")

public class UserService {

    //private UserManager userManager;
    private final UserDAO userDAO;
    private final InventoryDAO inventoryDAO;
    private final ItemDAO itemDAO;
    private final GameDAO gameDAO;

    public UserService() {
        //this.userManager = UserManagerImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();
        this.inventoryDAO = InventoryDAOImpl.getInstance();
        this.itemDAO = ItemDAOImpl.getInstance();
        this.gameDAO = GameDAOImpl.getInstance();
    }

    //Register User
    @POST
    @ApiOperation(value = "Register a new User", notes = "Name and Password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = User.class),
            @ApiResponse(code = 409, message = "User already exists"),
            @ApiResponse(code = 500, message = "Invalid inputs")
    })
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(RegisterCredentials rCr) {

        User user = new User(rCr.getName(), rCr.getPassword(), rCr.getMail());
        Item magicBerry = itemDAO.getItemByName("magicBerry");
        Inventory inventory = new Inventory(rCr.getName(), magicBerry.getName(), 0, magicBerry.getDescription(), magicBerry.getAvatar());
        if (rCr.getName().isEmpty() || rCr.getPassword().isEmpty() || rCr.getMail().isEmpty())
            return Response.status(500).build();
        else {
            if (userDAO.existsName(rCr.getName()) || userDAO.existsMail(rCr.getMail())) {
                return Response.status(409).build();
            } else {
                userDAO.addUser(user);
                inventoryDAO.createInventory(inventory);
                return Response.status(201).entity(user).build();
            }
        }
    }

    //Get a registered User
    @GET
    @ApiOperation(value = "Get a registered User", notes = "Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("name") String name) {

        User user = userDAO.getUser(name);

        if (user == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<User> entity = new GenericEntity<User>(user) {};
            return Response.status(200).entity(entity).build();
        }
    }

    //Get all registered Users
    @GET
    @ApiOperation(value = "Get all registered Users", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/userList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<User> userList = this.userDAO.getAllUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userList) {};
        return Response.status(200).entity(entity).build()  ;
    }


    //Update User
    @PUT
    @ApiOperation(value = "Update a User", notes = "oldName, newName, newPassword, newMail")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 406, message = "User already in use"),
            @ApiResponse(code = 409, message = "Mail already in use"),
            @ApiResponse(code = 500, message = "Invalid inputs")
    })
    @Path("/update/{name}")
    public Response updateUser(@PathParam("name") String oldName, RegisterCredentials rCr) {

        User oldUser = userDAO.getUser(oldName);

        if (!userDAO.existsName(oldName)) {
            return Response.status(404).build();
        } else {
            User newUser = new User(rCr.getName(), rCr.getPassword(), rCr.getMail());
            if (rCr.getName().isEmpty() && rCr.getPassword().isEmpty() && rCr.getMail().isEmpty())
                return Response.status(500).build();
            else {
                if (rCr.getName().isEmpty())
                    newUser.setName(oldName);
                if (rCr.getPassword().isEmpty())
                    newUser.setPassword(oldUser.getPassword());
                if (rCr.getMail().isEmpty())
                    newUser.setMail(oldUser.getMail());
                if (!oldName.equals(rCr.getName()) && userDAO.existsName(rCr.getName()))
                    return Response.status(406).build();
                if (!oldUser.getMail().equals(rCr.getMail()) && userDAO.existsMail(rCr.getMail()))
                    return Response.status(409).build();
                else {
                    userDAO.updateUserParameters(oldName, newUser);
                    inventoryDAO.updateUserName(oldName, newUser);
                    if (gameDAO.existsUserName(oldName))
                        gameDAO.updateUserName(oldName, newUser);
                    return Response.status(200).entity(newUser).build();
                }
            }
        }
    }

    //Update User Coins
    @PUT
    @ApiOperation(value = "Update the Coins of a User", notes = "userName and Coins")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/update/{name}/coins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserCoins(CoinsCredentials cCr) {

        User oldUser = userDAO.getUser(cCr.getUserName());
        if (cCr.getUserName().isEmpty())
            return Response.status(500).build();
        else {
            if (userDAO.existsName(cCr.getUserName())) {
                userDAO.updateUserCoinsByUserName(cCr.getUserCoins(), cCr.getUserName());
                User newUser = userDAO.getUser(cCr.getUserName());
                return Response.status(200).entity(newUser).build();
            } else {
                return Response.status(404).build();
            }
        }
    }

    //Delete User
    @DELETE
    @ApiOperation(value = "Delete a User", notes = "Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/delete/{name}")
    public Response deleteUser(@PathParam("name") String name) {

        User user = userDAO.getUser(name);
        if (userDAO.existsName(name)) {
            userDAO.deleteUserByName(name);
            inventoryDAO.deleteInventoryByUserName(name);
            return Response.status(200).entity(user).build();
        } else {
            return Response.status(404).build();
        }
    }

    //LogIn User
    @POST
    @ApiOperation(value = "LogIn User", notes = "Name and Password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Wrong password"),
            @ApiResponse(code = 500, message = "Invalid inputs")
    })
    @Path("/logIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logInUser(LogInCredentials lCr) {

        String name = lCr.getName();
        User user = userDAO.getUser(name);
        if ((lCr.getName().isEmpty()) || (lCr.getPassword().isEmpty())) {
            return Response.status(500).build();
        }

        if (userDAO.existsName(lCr.getName())){
            if (userDAO.getPasswordByName(lCr.getName(), lCr.getPassword())) {
                return Response.status(200).entity(user).build();
            }
            else
                return Response.status(405).build();
        }
        else
            return Response.status(404).build();
    }
}
