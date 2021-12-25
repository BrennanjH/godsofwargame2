/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonSubTypes({
    @Type(value = CreateUnitCommand.class),
    @Type(value = ChangeNickNameCommand.class)
})
public interface Command {
    
    //All commands have a series of tasks they attempt to complete, They start here
    public void execute(GameState gameState, WebSocketSession session);
    
    //returns true if a command has been fully prepped and can be expected to run without error
    public boolean isBuilt();
    
    //A debugging method that can be used to easily log the type of command
    public String testValue();
    
}
