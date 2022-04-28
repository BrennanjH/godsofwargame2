package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerValues;

/**
 * A class that contains methods maps a business PlayerValue to model PlayerValue(s)
 * designed to convert PlayerValues to PlayerValuesModel
 */
public class PlayerValuesMapper implements Mapper {

    /** A method that converts PlayerValues.java to PlayerValuesModel.java
     * @param businessValue The server's interpretation of the PlayerValue
     * @return A model of the business value that frontend clients should want
     */
    @Deprecated
    public static Model mapPlayerValuesToPlayerValuesModel(PlayerValues businessValue) {

        return new PlayerValuesModel(
                businessValue.getPoints(),
                businessValue.getCurrency(),
                businessValue.isReadyState(),
                businessValue.getUid()
        );
    }

    /** A method that typecasts the parameter into the mappers preferred type and then returns the mappers model
     * @param bo - a BusinessObject that will be transformed into a model
     * @return - A model of the business object
     */
    @Override
    public Model map(BusinessObject bo) {
        PlayerValues pv = (PlayerValues) bo;
        return new PlayerValuesModel(
                pv.getPoints(),
                pv.getCurrency(),
                pv.isReadyState(),
                pv.getUid()
        );
    }
}
