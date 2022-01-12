/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

/** A class that represents the current map the game is using
 *
 * @author brenn
 */
public class Map implements Changeable{ //May not need to be Changeable
    String mapName;

    public Map(){
        mapName = "Brennan's folly";
    }
    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public void addToChangeableQueue(GameState gameState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
