/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** The class represents the current game as an unbiased third party 
 * (players, units, Map, Teams)
 *
 * @author brenn
 */
@Component
public class GameState{
    
    //Link of Session Id To NickName
    private HashMap<String, StringBuilder> nickNames;
    
    //Session nickName to PlayerProfile
    private HashMap<StringBuilder, PlayerProfile> playerData;
    
    //As objects interact with the server they will be placed in one of the following for serialization
    private transient List<Changeable> changedObjects;
    private transient List<Destroyable> destroyed;
    private transient List<Createable> newObjects;
    
    private Map map;
    
    public LoadState loadState;


    public GameState(){
        
        playerData = new HashMap<>();
        nickNames = new HashMap<>();
        //players = new HashMap<>();
        
        //Create Queues
        changedObjects = new ArrayList<>();
        destroyed = new ArrayList<>();
        newObjects = new ArrayList<>();
        
        //Set loadState
        loadState = LoadState.PREGAME;
    }
    
    /** Adds a new player to the gamestate and sets up their references
     * 
     * @param newPlayer 
     */
    public void addPlayer(WebSocketSession newPlayer){
        //Set the player nickname
        StringBuilder temp = nickNames.put(newPlayer.getId(),
                new StringBuilder(newPlayer.getId()));
        
        //Using new nickName create playerData
        playerData.put(nickNames.get(newPlayer.getId()), new PlayerProfile(this, temp, newPlayer));
        
        //Add player Profile to newObject
        newObjects.add(playerData.get(temp));
        
        
    }
    
    /**NickNames are used to find some data to remove the use of session ids outside of 
     * command validation as such a nickName should only be allowed to change when
     * using this method
     * 
     * @param alteredUser
     * @param newNickName the new nickname requested by user
     */
    public void changeNickName(WebSocketSession alteredUser, String newNickName){
        //replace Original Nicknames value
        StringBuilder temp = nickNames.get(alteredUser.getId());
        temp.delete(0, temp.length());
        temp.append(newNickName);
        
    }
    /**
     * Removes all objects owned by the player from the server and adds it to removal queue
     * @param lostPlayer 
     */
    public void removePlayer( WebSocketSession lostPlayer){ //Order removed is important
        //Grab all the players related objects & keys
        StringBuilder playerName = nickNames.get(lostPlayer.getId());
        PlayerProfile focus = playerData.get(playerName);
        
        //Place the players respective objects into destroy for final removal
        destroyed.add(focus);
        
        //The Following must be removed in this order
        //Remove PlayerProfile
        playerData.remove(playerName);
        
        //Remove player from session object last
        nickNames.remove(lostPlayer.getId());
    }



    /**
     * Adds passed in value to gamestates changedObjects queue list
     * @param toBeAdded
     */
    public void addChangeableToQueue(Changeable toBeAdded){
        changedObjects.add(toBeAdded);
    }

    /**
     * Adds a passed in value to gamestates destroyed queue list
     * @param toBeAddedToQueue
     */
    public void addDestroyableToQueue(Destroyable toBeAddedToQueue){
        destroyed.add(toBeAddedToQueue);
    }

    /**
     * Adds passed in value to gamestates newObjects queue list
     * @param toBeAdded
     */
    public void addCreatableToQueue(Createable toBeAdded){
        newObjects.add(toBeAdded);
    }


//**GETTER AND SETTERS********************************************************************************************************************
    public List<Destroyable> getDestroyed() {
        return destroyed;
    }

    public HashMap<StringBuilder, PlayerProfile> getPlayerData() {
        return playerData;
    }
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Createable> getNewObjects() {
        return newObjects;
    }

    public HashMap<String, StringBuilder> getNickNames() {
        return nickNames;
    }

    public List<Changeable> getChangedObjects() {
        return changedObjects;
    }

    public StringBuilder getNickName(WebSocketSession session){
        return nickNames.get(session.getId());
    }
}
