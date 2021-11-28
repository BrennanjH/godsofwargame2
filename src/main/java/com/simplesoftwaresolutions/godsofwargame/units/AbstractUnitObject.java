/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerData;

/**
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class AbstractUnitObject implements Changeable{
    
//    ***CONSTRUCTORS********************************************************************************************************************************
    public AbstractUnitObject(){
        
    }

    
//    ***DECLARTION OF FIELDS********************************************************************************************************************************

    //Units values sometimes change and players need to know defaults to true
    public boolean change = true;
    //Subclasses should have direct access to their location, If necessary getters could also be used by subclasses
    protected float x , y , z;
    
    private PlayerData owner;
    
    /*Variables that define unit behaviors
    Speed - How fast the unit can move over time
    health - How much damage a unit can take before dying
    damage - How much Damage a unit can do per shot of weapon
    fireSpeed - How often the main weapon can fire
    targetingRange - How close a hostile unit must be before unit can fire
    */
    protected float speed, health, damage, fireSpeed, targetingRange;
    
//    ***ABSTRACTION FOR SUBCLASSES********************************************************************************************************************************
    
    //Most units can move to some degree
    abstract public void move();
    
    //Causes the Unit to attack, If the unit already has a target they will target
    abstract public void attack();

    //Units need to "die" in some sense but how they die is up to the unit's direct choice
    abstract public void removeSelf();
    
    //Units might have special properties in how they are created, Those are expressed by it's create Self
    //As Units are created by reflection from a frontend client a constructor is not a good place for this code.
    abstract public void createSelf();
    
    
//    ***GETTERS AND SETTERS********************************************************************************************************************************

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public PlayerData getOwner() {
        return owner;
    }

    public void setOwner(PlayerData owner) {
        change = true;
        this.owner = owner;
    }
    
//    ***LOGIC CODE********************************************************************************************************************************

    @Override
    public boolean hasChanged(){
        return change;
    }
    
}
