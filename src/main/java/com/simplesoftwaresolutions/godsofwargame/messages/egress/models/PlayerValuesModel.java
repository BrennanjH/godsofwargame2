package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.player.UserIdentity;

/**
 * A model of PlayerValues.java that is properly built for data sending
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class PlayerValuesModel implements Model{

    private int points;
    private int currency;

    private UserIdentity uid;

    public boolean readyState;

    public PlayerValuesModel(int points, int currency, boolean readyState ,UserIdentity uid) {
        this.uid = uid;
        this.points = points;
        this.currency = currency;
        this.readyState = readyState;
    }
}
