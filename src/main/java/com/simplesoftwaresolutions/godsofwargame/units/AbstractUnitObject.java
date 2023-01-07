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
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Changeable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Creatable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.StandardUnitMapper;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.Envelope;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author brenn
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
@JsonSubTypes({
    @Type(value = StandardUnit.class),
    @Type(value = CommandStructure.class)
})
public abstract class AbstractUnitObject implements Changeable, Destroyable, Creatable {
    
//    ***CONSTRUCTORS********************************************************************************************************************************
    public AbstractUnitObject(){

    }
    public AbstractUnitObject(AbstractMovementPlatform movementPlatform,
                              AbstractTurretPlatform turretPlatform,
                              InstanceId meta,
                              PositionalCord locationData) {
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
    protected PositionalCord locationData;

    //An object that stores reference information about the object
    protected InstanceId meta;

//End of Serializable fields ******************************************************************

    //The dsb is the queue that objects can add themselves too
    protected transient final DataServiceBus dsb = DataServiceBus.getInstance();

    private transient GameState gameState;
//    ***ABSTRACTION FOR SUBCLASSES********************************************************************************************************************************
    
    //convenience method to access movementPlatform move method
    public void move(){
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);

        movementPlatform.move(locationData, gameState.getBoardManager());
    }
    
    //convenience method to access turretPlatforms attack method
    public void attack(){
        turretPlatform.attack();
    }

    /**
     * Controlled method that allows units to be added to dsb after taking damage
     */
    public void takeDamage(Long damage){
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);

        movementPlatform.takeDamage(damage);
    }

    //Units need to "die" in some sense but how they die is up to the unit's direct control
    abstract public void removeSelf();

    abstract public boolean isBuilt();

    abstract protected int priceOfSelf();

    //Units might have special properties in how they are created, Those are expressed by its create Self
    //As Units are created by reflection from a frontend client a constructor is not a good place for this code. (Although Jackson makes it possible)
    public void createSelf(PlayerProfile owner){
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToCreatables(ev);

        owner.getPlayerValues().getUnits().add(this);

        //Check if owner can afford this unit
        if(owner.getPlayerValues().getCurrency() <= priceOfSelf()) {

            //Remove money from player
            owner.getPlayerValues().setCurrency(owner.getPlayerValues().getCurrency() - priceOfSelf());
        }
        
    }
    public void setRoute(List<PositionalCord> path){
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);

        //Set path
        this.movementPlatform.changeRoute(path);
    }
    
//    ***GETTERS AND SETTERS********************************************************************************************************************************


    public PositionalCord getLocationData() {
        return locationData;
    }

    public void setLocationData(PositionalCord locationData) {
        this.locationData = locationData;
    }

    public InstanceId getMeta() {
        return meta;
    }

    public void setMeta(InstanceId meta) {
        this.meta = meta;
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

    public List<PositionalCord> getPath(){
        return Collections.unmodifiableList(movementPlatform.getMovePath().getStepList()) ;
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
     * @return A string that protects the StringBuilder from any potential illegal changes
     */
    public String getOwnerNickName() {
        return meta.getOwnerNickName().toString();
    }

    public void setOwnerNickName(StringBuilder ownerNickName) {
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);

        meta.setOwnerNickName(ownerNickName);
    }

    /**
     * @return Returns a long that is not linked to unit's health so that other systems can't accidently alter player health
     */
    public long getHealthControlled(){
        return movementPlatform.getHealth().longValue();
    }

    /** A non-queue changing method that allows code to view but not alter unit instanceID
     * @return Returns a string that can't affect the class if it's modified
     */
    public String getInstanceId() {
        return meta.getInstanceId();
    }

    public void setInstanceId(String instanceId) {
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);

        meta.setInstanceId(instanceId);
    }
//End of Meta access methods
    
//    ***LOGIC CODE********************************************************************************************************************************

    public void setTarget(AbstractUnitObject targetedUnit) {

        turretPlatform.target.setTargetedUnit(targetedUnit);

        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);
    }
}
