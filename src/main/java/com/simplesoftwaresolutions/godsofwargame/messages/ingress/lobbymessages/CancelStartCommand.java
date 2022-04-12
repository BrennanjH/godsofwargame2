package com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.game.tasks.TaskStart;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.springframework.web.socket.WebSocketSession;

import java.util.TimerTask;

/**
 * A command sent by the games lobby host, No internal fields exist because this command assumes that countdown is stopping
 * cancels the first instance of a
 *
 */
public class CancelStartCommand implements Command {

    @JsonCreator
    public CancelStartCommand(){

    }

    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {
        //Verify loadState is LOBBY
        if(gameState.loadState == LoadState.LOBBY
                //Verify Command Sender is lobby Host
                && gameState.getPlayerFromSession(session).getServerRole() == ServerRole.LOBBY_HOST){
            int indexOf = -1;
            //Check if timer has a startgame task going
            for ( TimerTask t : gameState.getTasks()){
                //If a taskStart is found cancel it and save it's index for removal
                if(t.getClass() == TaskStart.class) {
                    t.cancel();
                    indexOf = gameState.getTasks().indexOf(t);
                    break;
                }
            }
            //remove task from task list
            if(indexOf != -1) {
                gameState.getTasks().remove(indexOf);
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
