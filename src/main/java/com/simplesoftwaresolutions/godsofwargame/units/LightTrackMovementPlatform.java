/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;
import com.simplesoftwaresolutions.godsofwargame.Terrain.TerrainTypes;
import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

/**
 *
 * @author brenn
 */
public class LightTrackMovementPlatform extends AbstractMovementPlatform {

    public TerrainTypes[] validTerrain = {TerrainTypes.PLAINS};

    public LightTrackMovementPlatform(){
        speed = 1;
        health = 5;
    }
    @Override
    protected void move(PositionalCord locationData, BoardManager boardManager) {
        PositionalCord moveTo;

        for(int i = 0; i < speed; i++){
            //get the next movement location
            moveTo = movePath.nextLocation();
            //If location is valid change unit location to that
            if(validateMove(locationData, moveTo, boardManager)){
                locationData.setX(moveTo.getX());
                locationData.setY(moveTo.getY());
            } else {
                //Clear units route as it is not valid if the next path is invalid
                movePath.clear();

                break; //with the move being invalid movement should stop
            }
        }



        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public int priceOfSelf() {
        return 200;
    }

    /** A method that checks if a coordinate is a valid position for this movement platform
     * @param checkCoord - the co-ordinate that is being checked
     * @return - true if passed in value is a possible move location
     */
    private boolean validateMove(PositionalCord startCoord,PositionalCord checkCoord, BoardManager manager){
        //Validate distance
        if ( Math.abs(startCoord.getX() - checkCoord.getX()) <= 1 &&
                Math.abs(startCoord.getY() - checkCoord.getY()) <= 1 ) {

            //Validate Terrain
            Terrain nextPosTerrain = manager.getTerrain(checkCoord.getX(), checkCoord.getY());
            for (TerrainTypes t :
                    validTerrain) {
                if (t == nextPosTerrain.getType()) {
                    return true;
                }
            }
        }
        return false;
    }

}
