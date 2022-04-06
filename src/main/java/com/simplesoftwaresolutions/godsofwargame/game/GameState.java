/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

/** The class represents the current game as an unbiased third party 
 * (players, units, Map, Teams)
 *
 * @author brenn
 */
@Component
@Scope("singleton")
public class GameState{
    //TODO - Update gameState objects to properly add themselves to the DataServiceBus
    //Link of Session Id To NickName
    private HashMap<String, StringBuilder> nickNames;
    
    //Session nickName to PlayerProfile
    private HashMap<StringBuilder, PlayerProfile> playerData;

    private Timer timer;
    //A not well protected list, Most alterations need a bit of logic
    private List<TimerTask> tasks;


    BoardManager boardManager;

    public LoadState loadState;

    @Deprecated
    public GameState(){
        
        playerData = new HashMap<>();
        nickNames = new HashMap<>();

        timer = new Timer();
        tasks = new ArrayList<>();
        //Set loadState
        loadState = LoadState.LOBBY;
    }

    @Autowired
    public GameState(BoardManager boardManager){

        playerData = new HashMap<>();
        nickNames = new HashMap<>();

        this.boardManager = boardManager; //Spring bean

        timer = new Timer();
        tasks = new ArrayList<>();
        //Set loadState
        loadState = LoadState.LOBBY;
    }
    
    /** Adds a new player to the GameState and sets up their references
     * 
     * @param newPlayer - A websocket session of a connecting client that is to be added a new player
     */
    public void addPlayer(WebSocketSession newPlayer){
        //Set the player nickname
        StringBuilder temp = nickNames.put(newPlayer.getId(),
                new StringBuilder(newPlayer.getId()));
        
        //Using new nickName create playerData
        playerData.put(nickNames.get(newPlayer.getId()), new PlayerProfile(this, temp, newPlayer));
        
//        //Add player Profile to newObject
//        newObjects.add(playerData.get(temp));
        
        
    }
    
    /**NickNames are used to find some data to remove the use of session ids outside of 
     * command validation as such a nickName should only be allowed to change when
     * using this method
     * 
     * @param alteredUser - the session object of the user whose nickname is changing
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
     * @param lostPlayer - the session object of the player who's leaving
     */
    public void removePlayer( WebSocketSession lostPlayer){ //Order removed is important
        //Grab all the players related objects & keys
        StringBuilder playerName = nickNames.get(lostPlayer.getId());
        PlayerProfile focus = playerData.get(playerName);
        
//        //Place the players respective objects into destroy for final removal
//        destroyed.add(focus);
        
        //The Following must be removed in this order
        //Remove PlayerProfile
        playerData.remove(playerName);
        
        //Remove player from session object last
        nickNames.remove(lostPlayer.getId());
    }





//**GETTER AND SETTERS********************************************************************************************************************


    public HashMap<StringBuilder, PlayerProfile> getPlayerData() {
        return playerData;
    }


    public HashMap<String, StringBuilder> getNickNames() {
        return nickNames;
    }


    public StringBuilder getNickName(WebSocketSession session){
        return nickNames.get(session.getId());
    }

    public PlayerProfile getPlayerFromSession(WebSocketSession session){
        return playerData.get(nickNames.get(session.getId()));
    }

    public void schedule( TimerTask task, long time){
        timer.schedule(task, time);

        //Register task with gameState
        tasks.add(task);
    }


    public List<TimerTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<TimerTask> tasks) {
        this.tasks = tasks;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }
}
