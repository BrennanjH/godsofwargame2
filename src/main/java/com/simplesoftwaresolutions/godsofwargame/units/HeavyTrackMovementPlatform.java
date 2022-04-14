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

/** A slow moving but higher health ground platform
 *
 * @author brenn
 */

public class HeavyTrackMovementPlatform extends AbstractMovementPlatform{

    private TerrainTypes[] validTerrain = {TerrainTypes.PLAINS};

    public HeavyTrackMovementPlatform() {
        speed = 1;
        health = 10;
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public int priceOfSelf() {
        return 300;
    }

    /** A method that checks if a coordinate is a valid position for this movement platform
     * @param checkCoord - the co-ordinate that is being checked
     * @return - true if passed in value is a possible move location
     */
    @Override
    protected boolean validateMove(PositionalCord startCoord,PositionalCord checkCoord, BoardManager manager){
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
