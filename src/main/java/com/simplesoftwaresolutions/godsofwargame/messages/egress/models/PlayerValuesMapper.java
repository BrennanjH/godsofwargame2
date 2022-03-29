package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerValues;

/**
 * A class that contains methods maps a business PlayerValue to model PlayerValue(s)
 */
public class PlayerValuesMapper {

    /** A method that converts PlayerValues.java to PlayerValuesModel.java
     * @param businessValue The server's interpretation of the PlayerValue
     * @return A model of the business value that frontend clients should want
     */
    public static Model mapPlayerValuesToPlayerValuesModel(PlayerValues businessValue) {

        return new PlayerValuesModel(
                businessValue.getPoints(),
                businessValue.getCurrency(),
                businessValue.isReadyState()
        );
    }
}
