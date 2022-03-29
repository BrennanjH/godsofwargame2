package com.simplesoftwaresolutions.godsofwargame.messages.models;


import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;

/**
 * A class that contains methods maps a business StandardUnit to model StandardUnit(s)
 */
public class StandardUnitMapper {
    public static Model mapStandardUnitToStandardUnitModel(StandardUnit su) {
        return new StandardUnitModel(su.getClassName(),
                su.getMovementPlatform(),
                su.getTurretPlatform(),
                su.getLocationData(),
                su.getMeta());
    }
}
