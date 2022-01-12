package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.springframework.web.socket.WebSocketSession;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class ChangeReadyState implements Command {
    public boolean changeToState;

    @JsonCreator
    public ChangeReadyState(@JsonProperty("readyState") boolean readyState){
        this.changeToState = readyState;
    }

    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {
        //Verify Loadstate is LOBBY
        if(gameState.loadState == LoadState.LOBBY){
            //get senders PlayerProfile //Set PlayerProfile's PlayerValue to changeToState
            gameState.getPlayerFromSession(session).getPlayerValues().setReadyState(changeToState);
        }

    }

    @Override
    public boolean isBuilt() {
        return false;
    }

}
