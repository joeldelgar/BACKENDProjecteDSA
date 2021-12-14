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

    private UserManager userManager; //
    private final UserDAO userDAO;
    private final InventoryDAO inventoryDAO;

    public UserService() {
        this.userManager = UserManagerImpl.getInstance(); //
        this.userDAO = UserDAOImpl.getInstance();
        this.inventoryDAO = InventoryDAOImpl.getInstance();
/*        if (userManager.userListSize() == 0) {
            userManager.addUser("Joel", "boss", "joel.delgado@estudiant.upc.edu");
            userManager.addUser("Maria", "caradura", "maria.garcia@estudiant.upc.edu");
            userManager.addUser("Miguel", "trolo", "miguel.mateos@estudiant.upc.edu");
            userManager.addUser("Arnau", "bunker", "arnau.millan@estudiant.upc.edu");
            userManager.addUser("Sergi", "alo", "sergi.picazo@estudiant.upc.edu");
        }*/
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
        Inventory inventory = new Inventory(rCr.getName());
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
            return Response.status(200).entity(user).build();
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


/*    //Update User - Actualitzar dades (mail, psw...)
    @PUT
    @ApiOperation(value = "Update a User", notes = "Update a User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Validation error")

    })
    @Path("/update")
    public Response updateUser(CredentialsLogIn upd, @PathParam("name") String oldName) {
        User uT = this.userManager.getUserName(oldName);
        User u = this.userManager.updateUser(uT,upd);
        if (u == null){
            return Response.status(404).build();
        }else {
            this.userManager.updateUser(u, upd);
            return Response.status(201).entity(u).build();
        }
    }
*/
    //Delete User
    @DELETE
    @ApiOperation(value = "Delete a User", notes = "Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/delete/{name}")
    public Response deleteUser(@PathParam("name") String name) {

        //User user = userDAO.getUser(name);

        if (userDAO.existsName(name)) {
            userDAO.deleteUserByName(name);
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

    //LogIn User
    @POST
    @ApiOperation(value = "LogIn User", notes = "Name, Password and Mail")
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

/*    //Get Logged Users
    @GET
    @ApiOperation(value = "Get Logged Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoggedUsers() {
        List<User> loggedUsers = this.userManager.getLoggedUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(loggedUsers) {};
        return Response.status(201).entity(entity).build()  ;

    }*//*

*//*    //Add Friend - Ids
    //NO FUNCIONA --> No em deixa afegir des de les dues bandes
    @POST
    @ApiOperation(value = "Add Friend")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User Not Found")
    })
    @Path("/friends/{id}/{friend}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFriends(@PathParam("id") String id, @PathParam("friend") String friend) {
        User u = this.userManager.getUser(id);
        if (u == null)
            return Response.status(404).build();

        User f = this.userManager.getUser(friend);
        if (f == null)
            return Response.status(404).build();

        for (User user: u.getFriendList()){
            if (user.equals(f))
                return Response.status(500).build();
        }

        u.addFriend(f);
        return Response.status(201).entity(f).build();
    }*//*

*//*    //Get Friends - Id
    @GET
    @ApiOperation(value = "Get Friends")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List")
    })
    @Path("/friends/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriends(@PathParam("id") String id) {
        User u = this.userManager.getUser(id);
        if (u==null)
            Response.status(404).build();
        List<User> friendList = u.getFriendList();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(friendList){};
        return Response.status(201).entity(entity).build()  ;

    }*//*

*//*    //Delete friend - Name
    @DELETE
    @ApiOperation(value = "Delete a Friend", notes = "Delete a friend by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/friends/{name}/{friend}")
    public Response deleteFriend(@PathParam("name") String name, @PathParam("friend") String friend) {
        User u = this.userManager.getUser(name);
        if (u == null)
            return Response.status(404).build();

        User f = this.userManager.getUser(friend);
        if (f == null)
            return Response.status(404).build();

        List<User> friendList = u.getFriendList();
        if (friendList.contains(f)){
            u.deleteFriend(f);
            return Response.status(201).entity(f).build();
        }
        else return Response.status(404).build();
    }*/
}
