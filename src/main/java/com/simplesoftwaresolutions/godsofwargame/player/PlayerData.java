package com.simplesoftwaresolutions.godsofwargame.player;


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

@Component("playerData")
public class PlayerData {
    private int points;
    private int currency;
    private List<AbstractUnitObject> units;
    private String nickName;
    
    private transient UserIdentity uid;
    
    public PlayerData( int currency) {
        this.points = 0;
        this.currency = currency;
    }
    public PlayerData( int currency, WebSocketSession newPlayer){
        uid = new UserIdentity(newPlayer);
        this.points = 0;
        this.currency = currency;
        nickName = uid.getNickname();
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

    public List<AbstractUnitObject> getUnits() {
        return units;
    }

    public void setUnits(List<AbstractUnitObject> units) {
        this.units = units;
    }
    
    
    
}
