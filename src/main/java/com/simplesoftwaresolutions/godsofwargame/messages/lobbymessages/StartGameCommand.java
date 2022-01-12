package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.game.SwitchToPreGameHandler;
import com.simplesoftwaresolutions.godsofwargame.game.TaskStart;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.springframework.web.socket.WebSocketSession;

import java.util.TimerTask;


/**
 * A command sent by the games lobby host, No internal fields exist because this command assumes that the game is starting
 * If not all players are ready starts a countdown timer of 10 seconds
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class StartGameCommand implements Command {

    @JsonCreator
    public StartGameCommand(){

    }

    /**
     * @param gameState The servers gameState object
     * @param session the session of the user who sent the request
     * @throws NullExpectedField Not thrown as no values are passed into this command
     */
    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {
        //Verify loadState is LOBBY
        if(gameState.loadState == LoadState.LOBBY
                //Verify Command Sender is lobby Host
                && gameState.getPlayerFromSession(session).serverRole == ServerRole.LOBBY_HOST ){
            boolean allPlayersReady = true;
            for (StringBuilder b:
                 gameState.getPlayerData().keySet()) {
                if ( !( gameState.getPlayerData().get(b).getPlayerValues().isReadyState() ) ) {
                    allPlayersReady = false;
                    break;
                }
            }
            //IF all players are ready start the game immediately
            if(allPlayersReady){
                SwitchToPreGameHandler handler = new SwitchToPreGameHandler();
                handler.SwitchToPreGame(gameState);
            } else {
                //If Not, Start a 10-second countdown Timer
                TimerTask task = new TaskStart(gameState);

                gameState.getTimer().schedule(task, 10000);
            }


            //Once started all player roles should be switch to GAME_MEMBER (this might be placed into a helper class)
        }

    }

    @Override
    public boolean isBuilt() {
        return false;
    }

}
