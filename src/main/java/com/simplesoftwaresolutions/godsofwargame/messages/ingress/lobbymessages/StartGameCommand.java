package com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.game.SwitchToPreGameHandler;
import com.simplesoftwaresolutions.godsofwargame.game.tasks.TaskStart;
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

    /** Starts a timer, after verifying validity of command, which will after 10 seconds begin the process of switching
     * the server from lobby-state to a pre-game state
     * @param gameState The servers gameState object
     * @param session the session of the user who sent the request
     * @throws NullExpectedField Not thrown as no values are passed into this command
     */
    @Override
    public void execute(GameState gameState, WebSocketSession session)  {
        //Verify loadState is LOBBY
        if(gameState.loadState == LoadState.LOBBY
                //Verify Command Sender is lobby Host
                && gameState.getPlayerFromSession(session).getServerRole() == ServerRole.LOBBY_HOST ){
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
                gameState.schedule(task, 10000);



            }

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
