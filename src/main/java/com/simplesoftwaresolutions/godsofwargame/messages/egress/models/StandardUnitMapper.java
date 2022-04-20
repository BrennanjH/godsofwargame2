package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;


import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;

/**
 * A class that contains methods maps a business StandardUnit to model StandardUnit(s)
 */
public class StandardUnitMapper implements Mapper{
    @Deprecated
    public static Model mapStandardUnitToStandardUnitModel(StandardUnit su) {
        return new StandardUnitModel(su.getClassName(),
                su.getMovementPlatform(),
                su.getTurretPlatform(),
                su.getLocationData(),
                su.getMeta());
    }

    @Override
    public Model map(BusinessObject bo) {
        StandardUnit su = (StandardUnit) bo;
        return new StandardUnitModel(su.getClassName(),
                su.getMovementPlatform(),
                su.getTurretPlatform(),
                su.getLocationData(),
                su.getMeta());
    }
}
