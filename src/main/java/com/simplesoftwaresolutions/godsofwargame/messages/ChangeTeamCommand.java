package com.simplesoftwaresolutions.godsofwargame.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.web.socket.WebSocketSession;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class ChangeTeamCommand implements Command{

    String switchTo;

    @JsonCreator
    public void ChangeTeamCommand(@JsonProperty("teamName") String newTeamName){
        switchTo = newTeamName;
    }

    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {
        //Get PlayerProfile of request sender
        
        //Add Team to players Team list

        //Add player to Teams playerList


    }

    @Override
    public boolean isBuilt() {
        return false;
    }

    @Override
    public String testValue() {
        return null;
    }
}
