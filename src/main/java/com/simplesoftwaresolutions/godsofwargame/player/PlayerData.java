package com.simplesoftwaresolutions.godsofwargame.player;


import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** A Class represents a players owned items; money, units, points, etc
 *
 * @author brenn
 */

public class PlayerData implements Changeable{
    //Boolean that signifies a change to one of the fields
    public boolean change = true;
    
    private int points;
    private int currency;
    private List<AbstractUnitObject> units;
    private String nickName;

    
    
    //Players have a many to many relationship with teams
    private List<Team> affiliatedTeams;
            
    private transient UserIdentity uid;
    
    public PlayerData( int currency) {
        change = true;
        this.points = 0;
        this.currency = currency;
    }
    public PlayerData( int currency, WebSocketSession newPlayer){
        change = true;
        uid = new UserIdentity(newPlayer);
        this.points = 0;
        this.currency = currency;
        nickName = uid.getNickname();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        change = true;
        this.points = points;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        change = true;
        this.currency = currency;
    }

    public List<AbstractUnitObject> getUnits() {
        return units;
    }

    public void setUnits(List<AbstractUnitObject> units) {
        this.units = units;
    }
    
    public List<Team> getAffiliatedTeams() {
        return affiliatedTeams;
    }

    public void setAffiliatedTeams(List<Team> affiliatedTeams) {
        change = true;
        this.affiliatedTeams = affiliatedTeams;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    
    @Override
    public boolean hasChanged() {
        return change;
    }
    
}
