package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

/**
 * A model of PlayerValues.java that is properly built for data sending
 */
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
