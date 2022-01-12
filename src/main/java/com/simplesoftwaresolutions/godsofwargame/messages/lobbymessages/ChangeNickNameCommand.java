/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.springframework.web.socket.WebSocketSession;

/** A command that changes a players nickname
 *
 * @author brenn
 */
public class ChangeNickNameCommand implements Command {

    String newNickName;
    @JsonCreator
    public ChangeNickNameCommand(@JsonProperty("newNickName") String name){
        this.newNickName = name;
    }
    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {
        if(gameState.loadState != LoadState.LOBBY){
            return;
        } else {
            if(newNickName == null)
                throw new NullExpectedField();
            gameState.changeNickName(session, newNickName);
            gameState.getChangedObjects()
                    .add(gameState.getPlayerData()
                            .get(gameState.getNickNames()
                                    .get(session.getId())));
        }
        
    }

    @Override
    public boolean isBuilt() {
        return newNickName != null;
    }


}
