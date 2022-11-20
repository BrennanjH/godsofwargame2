package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractMovementPlatform;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractTurretPlatform;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class StandardUnitModel implements Model {

    protected String className;

    protected AbstractMovementPlatform movementPlatform;

    protected AbstractTurretPlatform turretPlatform;

    //Stores where a unit exists in the world space
    protected PositionalCord locationData;

    //An object that stores reference information about the object
    protected InstanceId meta;

    public StandardUnitModel(String className,
                             AbstractMovementPlatform movementPlatform,
                             AbstractTurretPlatform turretPlatform,
                             PositionalCord locationData,
                             InstanceId meta) {
        this.className = className;
        this.movementPlatform = movementPlatform;
        this.turretPlatform = turretPlatform;
        this.locationData = locationData;
        this.meta = meta;
    }
}
