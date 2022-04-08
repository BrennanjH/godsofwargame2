/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.player;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Changeable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Creatable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Destroyable;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/** A class that represents the entirety of a player at once, 
 *
 * @author brenn
 */
//TODO - redesign objects and class so that changes in class allow for smaller data chunks for example changing a players
    //money should not force every single unit they own to also be sent to other players
public class PlayerProfile implements Destroyable, Creatable, Changeable{

    private PlayerValues playerValues; //Protects its own values
    private ServerRole serverRole; //value protected
    private UserIdentity uid;

    private List<Team> joinedTeams;

    private DataServiceBus dsb;

    public PlayerProfile( GameState gameState, StringBuilder id, WebSocketSession session){
        dsb = DataServiceBus.getInstance();

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

    /** a legal COPY of the PlayerProfiles serverRole
     * @return - a server role that if changed won't affect this object thus protecting the PlayerProfile from illegal changes
     */
    public ServerRole getServerRole() {
        return serverRole;
    }


    /** Sets the player role to a new role and adds them to message queue
     * @param serverRole - The role that the player is to become
     */
    public void setServerRole(ServerRole serverRole) {
        dsb.addToChangeables(this);

        this.serverRole = serverRole;
    }

    public List<Team> getJoinedTeams() {
        return joinedTeams;
    }

    public void setJoinedTeams(List<Team> joinedTeams) {
        this.joinedTeams = joinedTeams;
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

    public UserIdentity getUid() {
        return uid;
    }

    public String getId() {
        return uid.getId();
    }

    public String getNickname() {
        return uid.getNickname().toString();
    }
}
