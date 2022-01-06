package com.simplesoftwaresolutions.godsofwargame.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class Coalition implements Team{

    List<PlayerProfile> playerList;
    @JsonCreator
    public Coalition(@JsonProperty() String teamName){
        playerList = new ArrayList<>();
    }

    @Override
    public List<PlayerProfile> getPlayersOnTeam() {
        return null;
    }
}
