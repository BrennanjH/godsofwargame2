/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.StandardUnitMapper;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.Envelope;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

/** A general class that represents most units in the game,
 * These units require no special consideration in how they are created
 * or destroyed
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class StandardUnit extends AbstractUnitObject {

    @JsonCreator
    public StandardUnit(@JsonProperty("movementPlatform")AbstractMovementPlatform movementPlatform,
                        @JsonProperty("turretPlatform")AbstractTurretPlatform turretPlatform,
                        @JsonProperty("meta")InstanceId meta,
                        @JsonProperty("locationData") FullPositionalCord locationData){

        super( movementPlatform, turretPlatform, meta, locationData);
        dsb = DataServiceBus.getInstance();
    }
    public StandardUnit(){

    }
    @Override
    public void removeSelf() {
        //Create envelope
        Mapper mapper = new StandardUnitMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToDestroyables(ev);

        //get units owner from gameState
        PlayerProfile pv = getGameState().getPlayerData().get(meta.getOwnerNickName());

        //Remove unit from retrieved playerValues
        pv.removeUnit(this);
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
