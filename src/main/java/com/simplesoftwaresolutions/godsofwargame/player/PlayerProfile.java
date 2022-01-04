/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.player;

import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.Createable;
import com.simplesoftwaresolutions.godsofwargame.game.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/** A class that represents the entirety of a player at once, 
 *
 * @author brenn
 */
public class PlayerProfile implements Destroyable, Createable, Changeable{
    private List<AbstractUnitObject> units;
    private PlayerValues playerValues;
    private StringBuilder names;

    public PlayerProfile(){}
    public PlayerProfile( GameState gameState, StringBuilder id, WebSocketSession session){
        units = new ArrayList<>();
        playerValues = new PlayerValues(gameState, session);
        names = id;
    }
    public List<AbstractUnitObject> getUnits() {
        return units;
    }

    public void setUnits(List<AbstractUnitObject> units) {
        this.units = units;
    }

    public PlayerValues getPlayerValues() {
        return playerValues;
    }

    public void setPlayerValues(PlayerValues playerValues) {
        this.playerValues = playerValues;
    }

    public StringBuilder getNames() {
        return names;
    }

    public void setNames(StringBuilder names) {
        this.names = names;
    }

    @Override
    public void addToDestroyingQueue(GameState gameState) {
        gameState.addDestroyableToQueue(this);
        //gameState.getDestroyed().add(this);
    }

    @Override
    public void addToNewObjectsQueue(GameState gameState) {
        gameState.getNewObjects().add(this);
    }

    @Override
    public void addToChangeableQueue(GameState gameState) {
        gameState.getChangedObjects().add(this);
    }
    
    
}
