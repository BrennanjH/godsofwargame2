package com.simplesoftwaresolutions.godsofwargame.player;


import com.simplesoftwaresolutions.godsofwargame.messages.egress.Changeable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.PlayerValuesMapper;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.Envelope;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** A Class represents a players owned items; money, units, points, etc
 *
 * @author brenn
 */

public class PlayerValues implements Changeable{
    
    private int points;
    private int currency;

    private transient List<AbstractUnitObject> units;

    private UserIdentity uid;

    private boolean readyState;

    public PlayerValues(UserIdentity uid){
        this.uid = uid;
        units = new ArrayList<>();
        readyState = false;
        this.points = 0;
        this.currency = 25000;
        
    }

    public UserIdentity getUid() {
        return uid;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        
        this.points = points;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        
        this.currency = currency;
    }

    public void addUnit(AbstractUnitObject newUnit){

    }
    public List<AbstractUnitObject> getUnits() {
        return units;
    }

    public void removeUnit(AbstractUnitObject unitObject){
        units.remove(unitObject);


    }

    public void setUnits(List<AbstractUnitObject> units) {
        this.units = units;
    }


    public boolean isReadyState() {
        return readyState;
    }

    public void setReadyState(boolean readyState) {
        this.readyState = readyState;
    }


    public void deleteAllUnits() {
        DataServiceBus dsb = DataServiceBus.getInstance();

        units.clear();

        //Create envelope
        Mapper mapper = new PlayerValuesMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);
    }
}
