/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface Command {
    
    //All commands have a series of tasks they attempt to complete, They start here
    public void execute(GameState gameState, WebSocketSession session) throws NullExpectedField, InstanceIdMistmatchException;
    
    //returns true if a command has been fully prepped and can be expected to run without error
    public boolean isBuilt();

    public LoadState[] expectedLoadStates();
}
