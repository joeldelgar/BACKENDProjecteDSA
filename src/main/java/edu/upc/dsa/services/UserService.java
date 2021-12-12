package edu.upc.dsa.services;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.CredentialsLogIn;
import edu.upc.dsa.models.CredentialsRegister;
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
        if(manager.userListSize()==0){
            User u1 = new User("Joel","Password","joel.delgado@estudiant.upc.edu");
            User u2 = new User("Maria","CaraDura","maria.garcia@estudiant.upc.edu");
            User u3 = new User("Miguel","1234","miguel.mateos@estudiant.upc.edu");
            User u4 = new User("Arnau","5678","arnau.millan@estudiant.upc.edu");
            u1.setId("1");
            u2.setId("2");
            u3.setId("3");
            u4.setId("4");
            //u4.setMail("arnau.millan@estudiant.upc.edu");
            u3.friendList.add(u2);
            u3.friendList.add(u1);
            this.manager.addUser(u1);
            this.manager.addUser(u2);
            this.manager.addUser(u3);
            this.manager.addUser(u4);
        }
    }


    //Add User    Register
    @POST
    @ApiOperation(value = "Register a new User", notes = "Name and Password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(CredentialsRegister reg) {
        User user = new User(reg.getName(), reg.getPassword());
        //Joel ha eliminat reg.getMail()
        if (user.getName().equals("") || user.getPassword().equals("")){
            return Response.status(500).build();
        }
        for(User u: this.manager.getAllUsers()){
            if (u.getName().equals(user.getName()))
                return Response.status(500).build();
        }
        //set els paràmetres d'usuari que no siguin usuari i contrasenya
        //user.setMail(reg.getMail());
        //set l'id obtingut a la BD
        this.manager.addUser(user);
        return Response.status(200).entity(user).build();
    }

    //Update User - Actualitzar dades (mail, psw...)
    @PUT
    @ApiOperation(value = "Update a User", notes = "Update a User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Validation error")

    })
    @Path("/update")
    public Response updateUser(CredentialsLogIn upd, @PathParam("name") String oldName) {
        User uT = this.manager.getUserName(oldName);
        User u = this.manager.updateUser(uT,upd);
        if (u == null){
            return Response.status(404).build();
        }else {
            this.manager.updateUser(u, upd);
            return Response.status(201).entity(u).build();
        }
    }

    //Get User - Id
    @GET
    @ApiOperation(value = "Get a User", notes = "Get a User by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User user = this.manager.getUser(id);
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = this.manager.getAllUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    //Delete User - Id
    @DELETE
    @ApiOperation(value = "Delete a User", notes = "Delete a User by Credentials")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/delete/{name}")
    public Response deleteUser(@PathParam("name") String name) {
       User u = this.manager.getUser(name);
       if (u == null)
           return Response.status(404).build();
        else {
           this.manager.deleteUser(name);
           return Response.status(201).entity(u).build();
        }
    }

    //Login - Credentials
    @POST
    @ApiOperation(value = "Login user", notes = "Password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logUser(CredentialsLogIn credentialsLogin) {

        String name = credentialsLogin.getName();
        String password = credentialsLogin.getPassword();
        System.out.println(name+", "+ password);
        User u = this.manager.getUserLogin(name, password);

        if (u == null){
            return Response.status(404).build();
        }
        else if (u.getPassword().equals(password)) {
            this.manager.logInUser(name, password);
            return Response.status(200).entity(u).build();
        }
        else
            return Response.status(500).build();
    }

    //Get Friends - Id
    @GET
    @ApiOperation(value = "Get Friends")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List")
    })
    @Path("/friends/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriends(@PathParam("id") String id) {
        User u = this.manager.getUser(id);
        if (u==null)
            Response.status(404).build();
        List<User> friendList = u.getFriendList();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(friendList){};
        return Response.status(201).entity(entity).build()  ;

    }

    //Add Friend - Ids
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
        User u = this.manager.getUser(id);
        if (u == null)
            return Response.status(404).build();

        User f = this.manager.getUser(friend);
        if (f == null)
            return Response.status(404).build();

        for (User user: u.getFriendList()){
            if (user.equals(f))
                return Response.status(500).build();
        }

        u.addFriend(f);
        return Response.status(201).entity(f).build();
    }

    //Delete friend - Ids
    @DELETE
    @ApiOperation(value = "Delete a Friend", notes = "Delete a friend by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/friends/{id}/{friend}")
    public Response deleteFriend(@PathParam("id") String id, @PathParam("friend") String friend) {
        User u = this.manager.getUser(id);
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
