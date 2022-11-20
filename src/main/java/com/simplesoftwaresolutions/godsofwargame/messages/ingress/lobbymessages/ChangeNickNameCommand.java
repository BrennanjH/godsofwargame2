/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

/** A command that changes a players nickname
 *
 * @author brenn
 */
public class ChangeNickNameCommand implements Command {

    private static final Logger logger= LoggerFactory.getLogger(ChangeNickNameCommand.class);

    String newNickName;
    @JsonCreator
    public ChangeNickNameCommand(@JsonProperty("newNickName") String name){
        this.newNickName = name;
    }

    @Override
    public void execute(GameState gameState, WebSocketSession session)  {
        try {
            if (gameState.loadState != LoadState.LOBBY) {
                return;
            } else {
                if (newNickName == null)
                    throw new NullExpectedField("new nickname is null");
                gameState.changeNickName(session, newNickName);

            }
        } catch( Exception e ) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public boolean isBuilt() {
        return newNickName != null;
    }

    @Override
    public LoadState[] expectedLoadStates() {
        return new LoadState[] {LoadState.LOBBY};
    }

}
