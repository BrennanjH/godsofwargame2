/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerData;
import com.simplesoftwaresolutions.godsofwargame.player.Team;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.socket.WebSocketSession;

/** The class represents the current game as an unbiased third party 
 * (players, units, Map, Teams)
 *
 * @author brenn
 */
public class GameState{
    //Defines the relationShip players have
    private List<Team> teams;
    
    //Link of Session I'd To NickName
    private HashMap<String, StringBuilder> nickNames;
    
    //List of all players in the game mapped by their nickName
    private HashMap<StringBuilder, PlayerData> players;
    
    private HashMap<PlayerData, AbstractUnitObject> units;
    
    //As objects interact with the server they will be placed in one of the following for serialization
    private List<Changeable> changedObjects;
    private List<Destroyable> destroyed;
    private List<Createable> newObjects;
            
    private Map map;
    
    public GameState(){
        teams = new ArrayList<>();
        nickNames = new HashMap<>();
        players = new HashMap<>();
        changedObjects = new ArrayList<>();
        destroyed = new ArrayList<>();
    }
    
    /** Adds a new player to the gamestate and sets up their references
     * 
     * @param newPlayer 
     */
    public void addPlayer(WebSocketSession newPlayer){
        //Set the player nickname
        nickNames.put(newPlayer.getId(),
                new StringBuilder(newPlayer.getId()));
        
        //Using new nickName add player to 
        players.put(nickNames.get(newPlayer.getId()), new PlayerData(this, newPlayer));
    }
    
    /**NickNames are used to find some data to remove the use of session ids outside of 
     * command validation as such a nickName should only be allowed to change when
     * using this method
     * 
     * @param alteredUser
     * @param newNickName 
     */
    public void changeNickName(WebSocketSession alteredUser, String newNickName){
        //replace Original Nicknames value
        StringBuilder temp = nickNames.get(alteredUser.getId());
        temp.delete(0, temp.length());
        temp.append(newNickName);
        
    }
    public void removePlayer(WebSocketSession lostPlayer){
        //remove player from 
        players.remove(nickNames.get(lostPlayer.getId()));
        
        //Remove player from session object last
        nickNames.remove(lostPlayer.getId());
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public HashMap<PlayerData, AbstractUnitObject> getUnits() {
        return units;
    }

    public List<Createable> getNewObjects() {
        return newObjects;
    }
    
    public HashMap<String, StringBuilder> getNickNames() {
        return nickNames;
    }

    public void setNickNames(HashMap<String, StringBuilder> nickNames) {
        this.nickNames = nickNames;
    }

    public HashMap<StringBuilder, PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<StringBuilder, PlayerData> players) {
        this.players = players;
    }

    
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Changeable> getChangedObjects() {
        return changedObjects;
    }

    public List<Destroyable> getDestroyed() {
        return destroyed;
    }

    
}
