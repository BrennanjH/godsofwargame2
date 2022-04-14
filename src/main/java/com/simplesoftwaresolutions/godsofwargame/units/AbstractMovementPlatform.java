/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

import java.util.List;

/** an abstract class that defines the behavior of a tank movement platform
 *
 * @author brenn
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class AbstractMovementPlatform {
    
    /*Variables that define unit behaviors
    Speed - How fast the unit can move over time
    health - How much damage a unit can take before dying
    */
    protected float speed, health;

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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public abstract int priceOfSelf();
}
