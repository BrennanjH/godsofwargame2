package com.simplesoftwaresolutions.godsofwargame.player;


import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import org.springframework.web.socket.WebSocketSession;

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

public class PlayerValues implements Changeable {
    
    private int points;
    private int currency;

    private List<AbstractUnitObject> units;



    //Players have a many to many relationship with teams
    private List<Team> affiliatedTeams;



    public PlayerValues( GameState gameState, WebSocketSession newPlayer){
        units = new ArrayList<>();

        this.points = 0;
        this.currency = 25000;
        
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


    public void setUnits(List<AbstractUnitObject> units) {
        this.units = units;
    }
    public List<Team> getAffiliatedTeams() {
        return affiliatedTeams;
    }

    public void setAffiliatedTeams(List<Team> affiliatedTeams) {
        
        this.affiliatedTeams = affiliatedTeams;
    }

    @Override
    public void addToChangeableQueue(GameState gameState) {
        
    }



    
    
}
