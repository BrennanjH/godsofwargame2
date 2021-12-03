/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.SimpleTransform;
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
    protected SimpleTransform transform;
    
    protected PlayerData owner;
    
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

    public SimpleTransform getTransform() {
        return transform;
    }

    public void setTransform(SimpleTransform transform) {
        this.transform = transform;
    }

    public PlayerData getOwner() {
        return owner;
    }

    public void setOwner(PlayerData owner) {
        change = true;
        this.owner = owner;
    }

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

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(float fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    public float getTargetingRange() {
        return targetingRange;
    }

    public void setTargetingRange(float targetingRange) {
        this.targetingRange = targetingRange;
    }
    
    
//    ***LOGIC CODE********************************************************************************************************************************

    @Override
    public boolean hasChanged(){
        return change;
    }
    
}
