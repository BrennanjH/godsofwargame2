/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import org.springframework.web.socket.WebSocketSession;

/** A command that changes a players nickname
 *
 * @author brenn
 */
public class ChangeNickNameCommand implements Command{

    String newNickName;
    
    @Override
    public void execute(GameState gameState, WebSocketSession session) {
        if(gameState.loadState != LoadState.PREGAME){
            return;
        } else {
            gameState.changeNickName(session, newNickName);
            gameState.getChangedObjects().add(
                    gameState.getPlayerData().get(
                            gameState.getNickNames().get(session.getId())));
        }
        
    }

    @Override
    public boolean isBuilt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String testValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void injectGameState(com.simplesoftwaresolutions.godsofwargame.game.GameState gameState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void injectSession(WebSocketSession id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
