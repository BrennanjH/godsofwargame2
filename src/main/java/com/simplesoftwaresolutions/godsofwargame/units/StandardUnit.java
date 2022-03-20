/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
//TODO - integrate service bus with unit
/** A general class that represents most units in the game, 
 * These units require no special consideration in how they are created
 * or destroyed
 *
 * @author brenn
 */
public class StandardUnit extends AbstractUnitObject {

    @JsonCreator
    public StandardUnit(@JsonProperty("movementPlatform")AbstractMovementPlatform movementPlatform,
                        @JsonProperty("turretPlatform")AbstractTurretPlatform turretPlatform,
                        @JsonProperty("meta")InstanceId meta,
                        @JsonProperty("locationData") FullPositionalCord locationData){
        
        super( movementPlatform, turretPlatform, meta, locationData);
    }
    public StandardUnit(){

    }
    @Override
    public void removeSelf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isBuilt() {
        boolean nullCheck = (movementPlatform != null)
                && (turretPlatform != null)
                && (meta != null) ;
                //&& (transform != null);
        if(nullCheck) {
            return nullCheck && movementPlatform.isBuilt() && turretPlatform.isBuilt();
        } else {
            return false;
        }
    }

    /**
     * @return The sum of the Unit's movementPlatform, turretPlatform, and any other related fields regarding its type
     */
    @Override
    protected int priceOfSelf() {
        return movementPlatform.priceOfSelf() +
                turretPlatform.priceOfSelf() +
                500;
    }


}
