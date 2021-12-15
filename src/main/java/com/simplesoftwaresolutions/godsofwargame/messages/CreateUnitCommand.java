/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import javax.websocket.Session;
import org.springframework.web.socket.WebSocketSession;

/** A command that adds a new unit to the game and then properly informs players of it
 * Unit's implement their own createSelf() which will handle creation so only validation
 * is performed by this command
 * @author brenn
 */
public class CreateUnitCommand implements Command{
    
    
    private String className;
    
    private AbstractUnitObject unit;

    @Override
    public void execute(GameState gameState, WebSocketSession session) {
        unit.setGameState(gameState);
        
        unit.createSelf();
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
    public void injectGameState(GameState gameState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void injectSession(WebSocketSession id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
