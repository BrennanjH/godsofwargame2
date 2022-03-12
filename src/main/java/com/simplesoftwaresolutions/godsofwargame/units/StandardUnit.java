/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;

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
            @JsonProperty("meta")InstanceId meta){
        
        super( movementPlatform, turretPlatform, meta);
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


}
