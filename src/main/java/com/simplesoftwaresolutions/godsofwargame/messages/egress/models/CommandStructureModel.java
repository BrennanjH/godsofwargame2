package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Model;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class CommandStructureModel implements Model {

    FullPositionalCord locationData;
    InstanceId meta;
    String className;

    public CommandStructureModel(String className, FullPositionalCord locationData, InstanceId meta) {
        this.className = className;
        this.locationData = locationData;
        this.meta = meta;
    }
}
