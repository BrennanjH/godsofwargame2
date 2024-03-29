package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.CommandStructureMapper;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.Envelope;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

/**
 * A command structure follows the normal rules of a unit but on death or creation unique events might need to occur
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class CommandStructure extends AbstractUnitObject{

    @JsonCreator
    public CommandStructure(@JsonProperty("meta") InstanceId meta,
                        @JsonProperty("locationData") PositionalCord locationData){

        super( new StandStillMovementPlatform(), new NoGunTurretPlatform(), meta, locationData);
    }

    @Override
    public void removeSelf() {
        //Create envelope
        Mapper mapper = new CommandStructureMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToDestroyables(ev);

        //get units owner from gameState
        PlayerProfile pv = getGameState().getPlayerData().get(meta.getOwnerNickName());

        //Remove unit from retrieved playerValues
        pv.removeCommandUnit(this, getGameState());

    }

    @Override
    public boolean isBuilt() {
        return false;
    }

    @Override
    protected int priceOfSelf() {
        return 0;
    }
}
