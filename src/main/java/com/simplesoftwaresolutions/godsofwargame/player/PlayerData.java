package com.simplesoftwaresolutions.godsofwargame.player;


import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import java.util.List;
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

public class PlayerData implements Changeable,Destroyable {
    
    private int points;
    private int currency;
    
    //Players have a many to many relationship with teams
    private List<Team> affiliatedTeams;
    
    private UserIdentity uid;
    
    public PlayerData( GameState gameState, WebSocketSession newPlayer){
        
        uid = new UserIdentity(gameState, newPlayer);
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
    
    public List<Team> getAffiliatedTeams() {
        return affiliatedTeams;
    }

    public void setAffiliatedTeams(List<Team> affiliatedTeams) {
        
        this.affiliatedTeams = affiliatedTeams;
    }

    @Override
    public void addToSerializationQueue(GameState gameState) {
        
    }

    @Override
    public void addToDestroyingQueue(GameState gameState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
