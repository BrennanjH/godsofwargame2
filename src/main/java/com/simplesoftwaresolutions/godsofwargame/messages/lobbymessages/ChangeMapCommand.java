package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.Map;
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
    Map map;

    @JsonCreator
    public ChangeMapCommand (@JsonProperty("mapType") Map map) {
        this.map = map;
    }


    /** This will set the map object of the server to the map object sent,
     *
     * @param gameState The gameState object of the current server
     * @param session the session object of user who sent this command object
     * @throws NullExpectedField Thrown if a map object was not included in command
     */
    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {

        if(map == null){
            throw new NullExpectedField();
        }

        //Verify sender is LobbyHost
        if(gameState.getPlayerFromSession(session).getServerRole() == ServerRole.LOBBY_HOST){
            //Set gameState Map to mapName
            gameState.getBoardManager().setMap(map);
        }


    }

    @Override
    public boolean isBuilt() {
        return false;
    }

    @Override
    public LoadState[] expectedLoadStates() {
        return new LoadState[] {LoadState.LOBBY};
    }
}
