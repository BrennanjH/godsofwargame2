package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class CommandStructureModel implements Model {

    PositionalCord locationData;
    InstanceId meta;
    String className;

    public CommandStructureModel(String className, PositionalCord locationData, InstanceId meta) {
        this.className = className;
        this.locationData = locationData;
        this.meta = meta;
    }
}
