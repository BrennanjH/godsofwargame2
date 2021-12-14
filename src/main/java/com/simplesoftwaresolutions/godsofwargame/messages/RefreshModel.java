/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;

/** An object that holds onto a complete list of map data, Typically sent
 * to a new player or to a player who's sent messages have discrepancy errors
 * 
 * @author brenn
 */
public class RefreshModel extends AbstractReturnModel{

    private GameState gameState;
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
