/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.simplesoftwaresolutions.godsofwargame.game.Createable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import java.util.List;

/** A model that when recieved by the frontend will create a new unit matching 
 * these details
 *
 * @author brenn
 */
public class CreateObjectModel extends AbstractReturnModel{
    private List<Createable> newObjects;
    private transient GameState gameState;
    
    public CreateObjectModel(GameState gameState){
        newObjects = gameState.getNewObjects();
        this.gameState = gameState;
    }

    @Override
    public void build() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void build(GameState gameState) {
        this.gameState = gameState;
        build();
    }
}
