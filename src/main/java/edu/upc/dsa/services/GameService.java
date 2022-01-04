package edu.upc.dsa.services;
import edu.upc.dsa.DAO.*;
import edu.upc.dsa.managers.GameManager;
import edu.upc.dsa.managers.GameManagerImpl;
import edu.upc.dsa.models.*;

import io.swagger.annotations.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

@Api(value = "/game", description = "GameManager")
@Path("/game")

public class GameService {

    private GameManager gameManager;
    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    public GameService(){
        //this.gameManager = GameManagerImpl.getInstance();
        this.gameDAO = GameDAOImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();
/*        if(gameManager.gameListSize()==0){
            Game g1 = new Game(1, 0,0,3);
            Game g2 = new Game(2, 0,5,3);
            Game g3 = new Game(3, 0,2,3);
            this.gameManager.addGame(g1);
            this.gameManager.addGame(g2);
            this.gameManager.addGame(g3);

            Item o1 = new Item("Porta", "Passar", 0);
            Item o2= new Item("Ganzua","Eïna que et permet obrir panys", 0);
            Item o3= new Item("Gerro","Objecte a robar", 100);

            g1.addItem(o1);
            g1.addItem(o2);
            g2.addItem(o3);
            g3.addItem(o3);
        }*/
    }

    //Add Game
    @POST
    @ApiOperation(value = "Create a new Game", notes = "userName and Points")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response= Game.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 409, message = "Game already exists"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGame(GameCredentials gCr) {

        Game game = new Game(gCr.getUserName(), gCr.getPoints());

        if (gCr.getUserName().isEmpty())
            return Response.status(500).build();
        else {
            if (userDAO.existsName(gCr.getUserName())) {
                if (!gameDAO.existsUserName(gCr.getUserName())) {
                    gameDAO.addGame(game);
                    User user = userDAO.getUser(gCr.getUserName());
                    int op = (gCr.getPoints() / 10) + user.getCoins();
                    userDAO.updateUserCoinsByUserName(op, gCr.getUserName());
                    return Response.status(201).entity(game).build();
                } else
                    return Response.status(409).build();
            } else {
                return Response.status(404).build();
            }
        }
    }

    //Get the Game of a User
    @GET
    @ApiOperation(value = "Get the Game of a User", notes = "userName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("userName") String userName) {

        Game game = gameDAO.getGameByUserName(userName);

        if (game == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<Game> entity = new GenericEntity<Game>(game) {};
            return Response.status(200).entity(entity).build();
        }
    }

    //Get all Games
    @GET
    @ApiOperation(value = "Get all Games", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer="List")
    })
    @Path("/allGames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGames() {

        List<Game> gamaList = gameDAO.getAll();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(gamaList) {};
        return Response.status(200).entity(entity).build();
    }

    //Update Game
    @PUT
    @ApiOperation(value = "Update a Game", notes = "userName and Points")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Game.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/update/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGame(GameCredentials gCr) {

        Game oldGame = gameDAO.getGameByUserName(gCr.getUserName());
        Game game = new Game(gCr.getUserName(), gCr.getPoints());

        if (gCr.getUserName().isEmpty())
            return Response.status(500).build();
        else {
            if (userDAO.existsName(gCr.getUserName())) {
                gameDAO.updatePointsByUserName(gCr.getPoints(), gCr.getUserName());
                User user = userDAO.getUser(gCr.getUserName());
                int op = (gCr.getPoints()/10) - (oldGame.getPoints()/10) + user.getCoins();
                userDAO.updateUserCoinsByUserName(op, gCr.getUserName());
                return Response.status(200).entity(game).build();
            } else {
                return Response.status(404).build();
            }
        }
    }

/*    //Delete Game
    @DELETE
    @ApiOperation(value = "Delete a Game", notes = "Delete a Game by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/{id}")
    public Response deleteGame(@PathParam("id") int id) {
        Game game = this.gameManager.getGame(id);
        if (game == null){
            return Response.status(404).build();
        }
        else{
            this.gameManager.deleteGame(id);
            return Response.status(201).build();
        }
    }*/

/*    //Get item list from a game
    @GET
    @ApiOperation(value = "Get Item List", notes = "Get a item list by game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameItemList(@PathParam("id") int id) {
        Game g = this.gameManager.getGame(id);
        if (g == null){
            return Response.status(404).build();
        }else{
            List<Item> objectList = g.getItemList();
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(objectList){};
            return Response.status(201).entity(entity).build();
        }
    }*/

    //Get Item
    //Get Enemy
    //Get Level

}
