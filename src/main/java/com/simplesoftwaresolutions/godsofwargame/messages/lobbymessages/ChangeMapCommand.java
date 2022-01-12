package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.springframework.web.socket.WebSocketSession;

/**
 * A class that handles the changing of a games map, The map is represented by a string that can be used to identify
 * which map to load after game start
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class ChangeMapCommand implements Command {
    String mapName;

    @JsonCreator
    public ChangeMapCommand (@JsonProperty("mapName") String mapName) {
        this.mapName = mapName;
    }


    /** This will set the servers map object to a different map string which on ready will be loaded by all clients
     * No validation is performed to verify that the map exists, this is because the clients already have their own list
     * of maps to choose from and the owner of the lobby assumes all others have that map, Since the server doesn't perform
     * Checking of the map objects, even if the map doesn't exist and all the clients crash the server doesn't care
     *
     * @param gameState The gameState object of the current server
     * @param session the session object of user who sent this command object
     * @throws NullExpectedField Not thrown as no expected fields exist here
     */
    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {
        //Check that loadState is LOBBY
        if(gameState.loadState == LoadState.LOBBY){
            //Verify sender is LobbyHost
            if(gameState.getPlayerFromSession(session).serverRole == ServerRole.LOBBY_HOST){
                //Set gameState Map to mapName
                gameState.getMap().setMapName(mapName);
            }
        }

    }

    @Override
    public boolean isBuilt() {
        return false;
    }

}
