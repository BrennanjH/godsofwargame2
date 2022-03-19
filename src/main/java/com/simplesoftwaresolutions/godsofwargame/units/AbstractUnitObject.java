/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Changeable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Creatable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

/**
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
@JsonSubTypes({
    @Type(value = StandardUnit.class)
})
public abstract class AbstractUnitObject implements Changeable, Destroyable, Creatable {
    
//    ***CONSTRUCTORS********************************************************************************************************************************
    public AbstractUnitObject(){

    }
    public AbstractUnitObject(AbstractMovementPlatform movementPlatform,
                              AbstractTurretPlatform turretPlatform,
                              InstanceId meta,
                              FullPositionalCord locationData) {
        this.movementPlatform = movementPlatform;
        this.turretPlatform = turretPlatform;
        this.meta = meta;
        this.locationData = locationData;

    }
    
    
//    ***DECLARATION OF FIELDS********************************************************************************************************************************

    //The following Fields are to be serialized when sending this object to clients ***************
    protected String className;// = "com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject";
    
    protected AbstractMovementPlatform movementPlatform;

    protected AbstractTurretPlatform turretPlatform;

    //Stores where a unit exists in the world space
    protected FullPositionalCord locationData;

    //An object that stores reference information about the object
    protected InstanceId meta;


//End of Serializable fields ******************************************************************

    //The dsb is the queue that objects can add themselves too
    protected transient DataServiceBus dsb;

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

    abstract public boolean isBuilt();

    //Units might have special properties in how they are created, Those are expressed by it's create Self
    //As Units are created by reflection from a frontend client a constructor is not a good place for this code.
    public void createSelf(PlayerProfile owner){
        gameState.getNewObjects().add( this);
        owner.getPlayerValues().getUnits().add(this);
        
        //Check if player can afford unit
        //TODO
            //Remove money from player
            //TODO
        
    }
    
    
//    ***GETTERS AND SETTERS********************************************************************************************************************************



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


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


//Beginning of Meta access methods
    /** A non-queue changing method that allows code to view but not alter unit owner nickname
     * @return A string that protects the StringBuilder from anypotentiol illegal changes
     */
    public String getOwnerNickName() {
        return meta.getOwnerNickName().toString();
    }

    public void setOwnerNickName(StringBuilder ownerNickName) {
        //update message queue
        dsb.addToChangeables(this);

        meta.setOwnerNickName(ownerNickName);
    }

    /** A non-queue changing method that allows code to view but not alter unit instanceID
     * @return Returns a string that can't affect the class if it's modified
     */
    public String getInstanceId() {
        return meta.getInstanceId();
    }

    public void setInstanceId(String instanceId) {
        //Update message queue
        dsb.addToChangeables(this);

        meta.setInstanceId(instanceId);
    }
//End of Meta access methods
    
//    ***LOGIC CODE********************************************************************************************************************************


    public void setDSB(DataServiceBus dataServiceBus) {
        this.dsb = dataServiceBus;
    }
}
