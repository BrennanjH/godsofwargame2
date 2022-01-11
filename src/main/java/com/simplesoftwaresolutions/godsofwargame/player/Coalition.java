package com.simplesoftwaresolutions.godsofwargame.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class Coalition implements Team{
    String teamName;
    List<PlayerProfile> playerList;
    @JsonCreator
    public Coalition(@JsonProperty() String teamName){
        playerList = new ArrayList<>();
        this.teamName = teamName;
    }

    @Override
    public List<PlayerProfile> getPlayersOnTeam() {
        return playerList;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public Type getType() {
        return this.getClass();
    }
    /** A Method to remove  a PlayerProfile from passed Coalition
     * @param coalition The Coalition having PlayerProfiles Removed From
     * @param profile The PlayerProfile being removed from Coalition
     */
    public static void removeProfileFromCoalition(Coalition coalition, PlayerProfile profile){
        coalition.getPlayersOnTeam().remove(profile);
    }
}
