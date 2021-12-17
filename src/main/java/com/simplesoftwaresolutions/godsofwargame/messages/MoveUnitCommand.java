/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;


import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import org.springframework.web.socket.WebSocketSession;

/** A unit sent here is given a units transform and it's velocity
 * If a change occurs the frontend responsible will send a new moveunitcommand
 *
 * @author brenn
 */
public class MoveUnitCommand implements Command{

    private GameState gameState;
    
    private WebSocketSession messager;
    
    private AbstractUnitObject movingUnit;
    
    
    @Override
    public void execute(GameState gameState, WebSocketSession session) {
        
    }
    
    @Override
    public boolean isBuilt() {
        return gameState != null && movingUnit != null;
    }

    @Override
    public String testValue() {
        return "Command: [MoveUnitCommand]";
    }

    
    
    
}
