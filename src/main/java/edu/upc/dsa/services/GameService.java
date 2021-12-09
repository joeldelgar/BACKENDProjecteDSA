package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/game", description = "Endpoint to User Service")
@Path("/game")
public class GameService {
    private GameManager manager;

    public GameService(){
        this.manager= GameManagerImpl.getInstance();
        if(manager.gameListSize()==0){
            Game g1 = new Game(1, 0,0,3);
            Game g2 = new Game(2, 0,5,3);
            Game g3 = new Game(3, 0,2,3);
            this.manager.addGame(g1);
            this.manager.addGame(g2);
            this.manager.addGame(g3);

            Item o1 = new Item("Porta", "Passar", 0);
            Item o2= new Item("Ganzua","Eïna que et permet obrir panys", 0);
            Item o3= new Item("Gerro","Objecte a robar", 100);

            g1.addItem(o1);
            g1.addItem(o2);
            g2.addItem(o3);
            g3.addItem(o3);
        }
    }

    //Add Game
    @POST
    @ApiOperation(value = "Create a new Game", notes = "Create Game by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Game.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGame() {
        //id s'ha d'agafar del valor de la BD
        int id = this.manager.gameListSize() + 1;
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
    @GET
    @ApiOperation(value = "Get Object List", notes = "Get a object list by game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/object/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameObjectList(@PathParam("id") int id) {
        Game g = this.manager.getGame(id);
        if (g == null){
            return Response.status(404).build();
        }else{
            List<Item> objectList = g.getItemList();
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(objectList){};
            return Response.status(201).entity(entity).build();
        }
    }

    // Gets des de la BD

    //Get Object
    //Get Enemy
    //Get Level

}
