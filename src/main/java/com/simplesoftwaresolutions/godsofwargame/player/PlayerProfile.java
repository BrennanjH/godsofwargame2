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
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.PlayerProfileMapper;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.Envelope;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import com.simplesoftwaresolutions.godsofwargame.units.CommandStructure;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/** A class that represents the entirety of a player at once, 
 *
 * @author brenn
 */

public class PlayerProfile implements Destroyable, Creatable, Changeable{

    private PlayerValues playerValues; //Protects its own values
    private ServerRole serverRole; //value protected
    private UserIdentity uid;

    private List<Team> joinedTeams;

    private DataServiceBus dsb;

    public PlayerProfile( StringBuilder id, WebSocketSession session){
        dsb = DataServiceBus.getInstance();

        uid = new UserIdentity(session, id);
        playerValues = new PlayerValues(uid);
        joinedTeams = new ArrayList<>();
        serverRole = ServerRole.LOBBY_MEMBER;
    }

    public PlayerProfile(UserIdentity uid){
        dsb = DataServiceBus.getInstance();

        this.uid = uid;
        playerValues = new PlayerValues(uid);
        joinedTeams = new ArrayList<>();
        serverRole = ServerRole.LOBBY_MEMBER;
    }
    public PlayerProfile(GameState gameState, PlayerProfile playerProfile){

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
        //Create envelope
        Mapper mapper = new PlayerProfileMapper();
        Envelope ev = new Envelope( mapper,this);
        //store envelope
        dsb.addToChangeables(ev);

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

    public void removeUnit(AbstractUnitObject unitObject){
        playerValues.removeUnit(unitObject);
    }

    /** A method designed to remove commandObjects specifically
     * @param commandObject - the command object that is going to be removed
     * @param gameState - the gamestate object that will manage the removal of the PlayerProfile
     */
    public void removeCommandUnit(CommandStructure commandObject, GameState gameState){
        playerValues.removeUnit(commandObject);

        List<AbstractUnitObject> units = playerValues.getUnits();

        //check if any other commandStructures exist all's well if not the player needs to be removed
        for (AbstractUnitObject focus :
                units) {
            if (focus instanceof CommandStructure) {
                return;
            }
        }
        gameState.setPlayerToSpectator(this);

    }

    public void roleToSpectator() {
        serverRole = ServerRole.SPECTATOR;

        playerValues.deleteAllUnits();
    }
}
