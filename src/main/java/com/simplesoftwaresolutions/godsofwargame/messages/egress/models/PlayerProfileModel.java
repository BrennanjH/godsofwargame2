package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerValues;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import com.simplesoftwaresolutions.godsofwargame.player.Team;
import com.simplesoftwaresolutions.godsofwargame.player.UserIdentity;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class PlayerProfileModel implements Model{

    private PlayerValues playerValues;
    private ServerRole serverRole;
    private UserIdentity uid;
    private List<Team> joinedTeams;

    public PlayerProfileModel(ServerRole serverRole, UserIdentity uid, List<Team> joinedTeams, PlayerValues playerValues) {
        this.playerValues = playerValues;
        this.serverRole = serverRole;
        this.uid = uid;
        this.joinedTeams = joinedTeams;
    }
}
