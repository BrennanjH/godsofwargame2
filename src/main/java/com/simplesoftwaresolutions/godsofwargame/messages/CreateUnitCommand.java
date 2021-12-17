/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.web.socket.WebSocketSession;
import com.simplesoftwaresolutions.godsofwargame.units.*;

/** A command that adds a new unit to the game and then properly informs players of it
 * Unit's implement their own createSelf() which will handle creation so only validation
 * is performed by this command
 * @author brenn
 */
public class CreateUnitCommand implements Command{
    private String className;
    
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
    private AbstractUnitObject unit;

    @Override
    public void execute(GameState gameState, WebSocketSession session) {
        System.out.println("Beginning command execution: " + testValue());
        StringBuilder commandMaker = gameState.getNickNames().get(session.getId());
        System.out.println("CreateUnitCommand: execute(): Checking state of field commandMaker; " + commandMaker == null);
        System.out.println("CreateUnitCommand: execute(): Checking state of field commandMaker; " + commandMaker);
        
        System.out.println("CreateUnitCommand: execute(): Checking state of field unit; " + unit != null);
        System.out.println("CreateUnitCommand: execute(): Checking state of field unit; " + unit);
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
        return (unit != null);
    }

    @Override
    public String testValue() {
        return "Command: [CreateUnitCommand]";
    }



    
}
