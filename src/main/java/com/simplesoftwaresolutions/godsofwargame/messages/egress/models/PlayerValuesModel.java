package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A model of PlayerValues.java that is properly built for data sending
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class PlayerValuesModel implements Model{

    private int points;
    private int currency;
    public boolean readyState;

    public PlayerValuesModel(int points, int currency, boolean readyState) {
        this.points = points;
        this.currency = currency;
        this.readyState = readyState;
    }
}
