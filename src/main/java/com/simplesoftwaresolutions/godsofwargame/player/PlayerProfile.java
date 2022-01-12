/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.player;

import com.simplesoftwaresolutions.godsofwargame.game.Changeable;
import com.simplesoftwaresolutions.godsofwargame.game.Createable;
import com.simplesoftwaresolutions.godsofwargame.game.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/** A class that represents the entirety of a player at once, 
 *
 * @author brenn
 */
public class PlayerProfile implements Destroyable, Createable, Changeable{

    private PlayerValues playerValues;
    public ServerRole serverRole;
    private UserIdentity uid;

    private List<Team> joinedTeams;


    public PlayerProfile( GameState gameState, StringBuilder id, WebSocketSession session){
        uid = new UserIdentity(gameState, session, id);
        playerValues = new PlayerValues(gameState, session);
        joinedTeams = new ArrayList<>();
        serverRole = ServerRole.LOBBY_MEMBER;
    }

    public PlayerValues getPlayerValues() {
        return playerValues;
    }

    public void setPlayerValues(PlayerValues playerValues) {
        this.playerValues = playerValues;
    }


    public List<Team> getJoinedTeams() {
        return joinedTeams;
    }

    public void setJoinedTeams(List<Team> joinedTeams) {
        this.joinedTeams = joinedTeams;
    }


    @Override
    public void addToDestroyingQueue(GameState gameState) {
        gameState.addDestroyableToQueue(this);
        //gameState.getDestroyed().add(this);
    }

    @Override
    public void addToNewObjectsQueue(GameState gameState) {
        gameState.getNewObjects().add(this);
    }

    @Override
    public void addToChangeableQueue(GameState gameState) {
        gameState.getChangedObjects().add(this);
    }


    public UserIdentity getUid() {
        return uid;
    }


    /** A method to remove a Coalition from A player Profile
     * @param coalition The coalition that is being removed
     * @param profile the profile that the coalition is being removed from
     */
    public static void removeCoalitionFromProfile(Coalition coalition, PlayerProfile profile){
        profile.getJoinedTeams().remove(coalition);
    }

    /** gets list of
     * @return Returns a List of all PlayerProfiles Currently joined Coalitions
     */
    public List<Coalition> getProfileCoalitions(){
        List<Coalition> oldTeams = new ArrayList<>();
        for( Team T : this.getJoinedTeams() ) {
            if( T.getType() == Coalition.class){
                oldTeams.add((Coalition) T);
            }
        }
        return oldTeams;
    }
}
