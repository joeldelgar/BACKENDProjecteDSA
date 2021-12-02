package edu.upc.dsa.services;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/user", description = "Endpoint to User Service")
@Path("/user")
public class UserService {
    private UserManager manager;

    public UserService(){
        //manager fora, implementació del DAO
        this.manager= UserManagerImpl.getInstance();
        if(manager.userListsize()==0){
            User u1 = new User("Joel","Password");
            User u2 = new User("Maria","CaraDura");
            User u3 = new User("Miguel","1234");
            User u4 = new User("Sergi","5678");
            u3.friendList.add(u2);
            u3.friendList.add(u1);
            this.manager.addUser(u1);
            this.manager.addUser(u2);
            this.manager.addUser(u3);
            this.manager.addUser(u4);
        }
    }

    //Add User
    /*@POST
    @ApiOperation(value = "Add a new User", notes = "Name and Password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Credentials credentials) {
        User user = new User(credentials.getName(), credentials.getPassword());
        if (user.getName()==null || user.getPsw()==null)
            return Response.status(500).build();
        this.manager.addUser(user);
        return Response.status(201).entity(user).build();
    }*/

    //@Path("/{name}/{psw}")
    //    @Consumes(MediaType.APPLICATION_JSON)
    //    public Response newUser(@PathParam("name") String name, @PathParam("psw") String psw) {
    //        User user = new User(name, psw);
    //        if (user.getName()==null || user.getPsw()==null)
    //            return Response.status(500).build();
    //        this.manager.addUser(user);
    //        return Response.status(201).entity(user).build();
    //    }

    //Update User
    @PUT
    @ApiOperation(value = "Update a User", notes = "Update a User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{name}/{psw}")
    public Response updateUser(@PathParam("name") String name, @PathParam("psw") String psw) {
        User u = this.manager.getUser(name);
        if (u == null){
            return Response.status(404).build();
        }else{
            this.manager.updateUser(u, psw);
            return Response.status(201).entity(u).build();
        }
    }

    //Get User
    @GET
    @ApiOperation(value = "Get a User", notes = "Get a User by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("name") String name) {
        User user = this.manager.getUser(name);
        if (user == null){
            return Response.status(404).build();
        }else{
            return Response.status(201).entity(user).build();
        }
    }

    //Get All Users
    @GET
    @ApiOperation(value = "Get all Users", notes = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = this.manager.getAllUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    //Delete User
    @DELETE
    @ApiOperation(value = "Delete a User", notes = "Delete an User By Name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{name}")
    public Response deleteUser(@PathParam("name") String name) {
        User user = this.manager.getUser(name);
        if (user == null){
            return Response.status(404).build();
        }
        else{
            this.manager.deleteUser(name);
            return Response.status(201).entity(user).build();
        }
    }

    //Login
    @POST
    @ApiOperation(value = "Login user", notes = "Password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logUser(Credentials credentials) {

        String name = credentials.getName();
        String password = credentials.getPassword();
        System.out.println(name+", "+ password);
        User u = this.manager.getUser(name);

        if (u == null){
            return Response.status(404).build();
        }
        else if (u.getPsw().equals(password)) {
            this.manager.logUser(name, password);
            return Response.status(200).entity(u).build();
        }
        else
            return Response.status(500).build();
    }

    //Get Friends
    @GET
    @ApiOperation(value = "Get Friends")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List")
    })
    @Path("/friends/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriends(@PathParam("name") String name) {
        User u = this.manager.getUser(name);
        List<User> friendList = u.getFriendList();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(friendList){};
        return Response.status(201).entity(entity).build()  ;

    }

    //Add Friend
    @POST
    @ApiOperation(value = "Add Friend")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Path("/friends/{name}/{friend}")
    @Consumes(MediaType.APPLICATION_JSON)
    //M'afegeix els amics que no ho són per cap dels dos però els que ho són per una banda peten
    public Response addFriend(@PathParam("name") String name, @PathParam("friend") String friend) {
        User u = this.manager.getUser(name);
        if (u == null)
            return Response.status(500).build();
        User f = this.manager.getUser(friend);
        if (f == null)
            return Response.status(500).build();

        List<User> friendList = u.getFriendList();
        if (friendList.contains(f))
            return Response.status(404).build();

        u.addFriend(f);
        return Response.status(201).entity(f).build();
    }

    //Delete friend
    @DELETE
    @ApiOperation(value = "Delete a Friend", notes = "Delete a friend by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/friends/{name}/{friend}")
    public Response deleteFriend(@PathParam("name") String name, @PathParam("friend") String friend) {
        User u = this.manager.getUser(name);
        if (u == null)
            return Response.status(404).build();

        User f = this.manager.getUser(friend);
        if (f == null)
            return Response.status(404).build();

        List<User> friendList = u.getFriendList();
        if (friendList.contains(f)){
            u.deleteFriend(f);
            return Response.status(201).entity(f).build();
        }
        else return Response.status(404).build();
    }

    //Get Logged Users
    @GET
    @ApiOperation(value = "Get Logged Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoggedUsers() {
        List<User> loggedUsers = this.manager.getLoggedUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(loggedUsers) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
