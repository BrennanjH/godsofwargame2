/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.Createable;
import com.simplesoftwaresolutions.godsofwargame.game.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.game.SimpleTransform;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

/**
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class AbstractUnitObject implements Changeable, Destroyable, Createable{
    
//    ***CONSTRUCTORS********************************************************************************************************************************
    
    
//    ***DECLARTION OF FIELDS********************************************************************************************************************************

    //The following Fields are to be serialized when sending this object to clients ***************
    protected String className;// = "com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject";
    
    protected AbstractMovementPlatform movementPlatform;

    protected AbstractTurretPlatform turretPlatform;
    
    //An object that stores refrence information about the object
    public InstanceId meta;
    
    //Subclasses should have direct access to their location, If necessary getters could also be used by subclasses
    protected SimpleTransform transform;
    //End of Serializable fields ******************************************************************
    
    private transient GameState gameState;
//    ***ABSTRACTION FOR SUBCLASSES********************************************************************************************************************************
    
    //convenience method to access movementplatform move method
    public void move(){
        movementPlatform.move();
    }
    
    //convenience method to access turretPlatforms attack method
    public void attack(){
        turretPlatform.attack();
    }

    //Units need to "die" in some sense but how they die is up to the unit's direct choice
    abstract public void removeSelf();
    
    //Units might have special properties in how they are created, Those are expressed by it's create Self
    //As Units are created by reflection from a frontend client a constructor is not a good place for this code.
    public void createSelf(PlayerProfile owner){
        gameState.getNewObjects().add( this);
        owner.getUnits().add(this);
        
        //Check if player can afford unit
        //TODO
            //Remove money from player
            //TODO
        
    }
    
    
//    ***GETTERS AND SETTERS********************************************************************************************************************************

    public SimpleTransform getTransform() {
        return transform;
    }

    public void setTransform(SimpleTransform transform) {
        this.transform = transform;
    }

    public AbstractMovementPlatform getMovementPlatform() {
        return movementPlatform;
    }

    public void setMovementPlatform(AbstractMovementPlatform movementPlatform) {
        this.movementPlatform = movementPlatform;
    }

    public AbstractTurretPlatform getTurretPlatform() {
        return turretPlatform;
    }

    public void setTurretPlatform(AbstractTurretPlatform turretPlatform) {
        this.turretPlatform = turretPlatform;
    }

    public InstanceId getMeta() {
        return meta;
    }

    public void setMeta(InstanceId meta) {
        this.meta = meta;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    
    
    
    
//    ***LOGIC CODE********************************************************************************************************************************

    @Override
    public void addToSerializationQueue(GameState gameState) {
        gameState.getChangedObjects().add(this);
    }
    
    @Override
    public void addToDestroyingQueue(GameState gameState){
        gameState.getDestroyed().add(this);
    }

    @Override
    public void addToNewObjectsQueue(GameState gameState){
        gameState.getNewObjects().add(this);
    }
    
    
}
