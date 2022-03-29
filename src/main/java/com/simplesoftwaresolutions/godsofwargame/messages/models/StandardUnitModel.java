package com.simplesoftwaresolutions.godsofwargame.messages.models;

import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractMovementPlatform;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractTurretPlatform;

public class StandardUnitModel implements Model {

    //The following Fields are to be serialized when sending this object to clients ***************
    protected String className;// = "com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject";

    protected AbstractMovementPlatform movementPlatform;

    protected AbstractTurretPlatform turretPlatform;

    //Stores where a unit exists in the world space
    protected FullPositionalCord locationData;

    //An object that stores reference information about the object
    protected InstanceId meta;

    public StandardUnitModel(String className,
                             AbstractMovementPlatform movementPlatform,
                             AbstractTurretPlatform turretPlatform,
                             FullPositionalCord locationData,
                             InstanceId meta) {
        this.className = className;
        this.movementPlatform = movementPlatform;
        this.turretPlatform = turretPlatform;
        this.locationData = locationData;
        this.meta = meta;
    }
}
