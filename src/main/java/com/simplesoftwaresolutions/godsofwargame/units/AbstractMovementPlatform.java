/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import lombok.Data;

import java.util.List;

/** an abstract class that defines the behavior of a tank movement platform
 *
 * @author brenn
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public abstract class AbstractMovementPlatform {
    
    /*Variables that define unit behaviors
    Speed - How fast the unit can move over time
    health - How much damage a unit can take before dying
    */
    protected Long speed =1L, health =1L;

    protected Route movePath = new Route();

    public void changeRoute(List<PositionalCord> newRoute){
        movePath.setStepList(newRoute);
    }

    public Route getMovePath() {
        return movePath;
    }

    protected void move(PositionalCord locationData, BoardManager boardManager){
        PositionalCord moveTo;

        for(int i = 0; i < speed; i++){
            //get the next movement location
            if(movePath.getStepList().isEmpty()){
                break;
            }
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

    }

    protected abstract boolean validateMove(PositionalCord locationData, PositionalCord moveTo, BoardManager boardManager) ;


    abstract public boolean isBuilt();


    /** This method is called when other units are attacking this unit, and it will calculate the remaining health of the unit after taking damage
     * @param damage - the damage that an attacking unit is wanting to do to this unit
     */
    public void takeDamage(Long damage){
        health -= damage;
    }
    public abstract int priceOfSelf();

}
