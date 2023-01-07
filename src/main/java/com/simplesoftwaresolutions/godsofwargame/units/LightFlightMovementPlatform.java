package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

public class LightFlightMovementPlatform extends AbstractMovementPlatform{

    public LightFlightMovementPlatform(){
        speed = 1L;
        health = 2L;
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

            return true;
        }
        return false;
    }


    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public int priceOfSelf() {
        return 0;
    }
}
