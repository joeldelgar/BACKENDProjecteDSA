package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to User Service")
@Path("/game")
public class GameService {
    private GameManager manager;

    public GameService(){
        this.manager= GameManagerImpl.getInstance();
    }

    //Add Game
    @POST
    @ApiOperation(value = "Create a new Game", notes = "Create Game by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Game.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newGame(@PathParam("id") int id) {
        Game g = this.manager.getGame(id);
        if (g!=null) return Response.status(500).entity(g).build();
        Game game = new Game(id,0,0,3);
        this.manager.addGame(game);
        return Response.status(201).entity(game).build();
    }

    //Get Game
    @GET
    @ApiOperation(value = "Get a Game", notes = "Get a Game by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("id") int id) {
        Game game = this.manager.getGame(id);
        if (game == null){
            return Response.status(404).build();
        }else{
            return Response.status(201).entity(game).build();
        }
    }

    //Delete Game
    @DELETE
    @ApiOperation(value = "Delete a Game", notes = "Delete a Game by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/{id}")
    public Response deleteGame(@PathParam("id") int id) {
        Game game = this.manager.getGame(id);
        if (game == null){
            return Response.status(404).build();
        }
        else{
            this.manager.deleteGame(id);
            return Response.status(201).build();
        }
    }

    //Get object list from a user

}
