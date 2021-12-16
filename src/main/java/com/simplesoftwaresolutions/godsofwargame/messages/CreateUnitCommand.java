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
        StringBuilder commandMaker = gameState.getNickNames().get(session.getId());
        //Make sure the Command Issuer is the same person as the units Owner
        if(commandMaker.compareTo(unit.getMeta().getOwnerNickName()) == 0){
            //Set the Unit's string builder to the sessions
            unit.getMeta().setOwnerNickName(commandMaker);
            
            //Inject Dependents
            unit.setGameState(gameState);
            
            //Allow the Unit to handle it's own creation
            unit.createSelf(gameState.getPlayerData().get(commandMaker));
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
    public void injectGameState(GameState gameState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void injectSession(WebSocketSession id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
