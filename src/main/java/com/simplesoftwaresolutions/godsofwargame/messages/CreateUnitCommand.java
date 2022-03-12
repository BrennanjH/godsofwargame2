/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.web.socket.WebSocketSession;
import com.simplesoftwaresolutions.godsofwargame.unitsDEPRECATED.*;

/** A command that adds a new unit to the game and then properly informs players of it
 * Unit's implement their own createSelf() which will handle creation so only validation
 * is performed by this command
 * @author brenn
 */
public class CreateUnitCommand implements Command{
    
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
    private AbstractUnitObject unit;
    
    @JsonCreator
    public CreateUnitCommand(@JsonProperty("unit")AbstractUnitObject unit){
        this.unit = unit;
    }
    
    @Override
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField {

        if( !unit.isBuilt() ){
            System.out.println("^Command CreateUnit Failed To Create Unit^");
            throw new NullExpectedField();
        }

        StringBuilder commandMaker = gameState.getNickNames().get(session.getId());
        //Make sure the Command Issuer is the same person as the units Owner
        if(commandMaker.compareTo(unit.getMeta().getOwnerNickName()) == 0){
            System.out.println("CreateUnitCommand: Execute(): conditional passed");
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

    public AbstractUnitObject getUnit() {
        return unit;
    }
    
    
    public void setUnit(AbstractUnitObject unit) {
        this.unit = unit;
    }
    
}
