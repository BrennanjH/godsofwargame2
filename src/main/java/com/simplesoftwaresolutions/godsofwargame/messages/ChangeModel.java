/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import java.util.ArrayList;
import java.util.List;

/** An object that holds onto the objects that have changed since the last message sent
 * does not send non-changed objects
 *
 * @author brenn
 */
public class ChangeModel extends AbstractReturnModel{

    private List<Changeable> update;
    private List<Destroyable> remove;
    
    public ChangeModel(GameState gameState){
        this.gameState = gameState;
        gameState.getChangedObjects();
        gameState.getChangedObjects();
        gameState.getDestroyed();
    }
    @Override
    public void build() {
        gameState.getPlayers();
    }
    @Override
    public void build(GameState gameState){
        this.gameState = gameState;
        
        build();
    }
    
}
