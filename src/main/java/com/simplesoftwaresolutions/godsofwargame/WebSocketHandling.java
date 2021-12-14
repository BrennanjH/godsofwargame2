/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.messages.ChangeModel;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/** The websocket handler that handles how messages are handled in different situations such as joining, leaving, and talking
 *
 * @author brenn
 */
public class WebSocketHandling extends AbstractWebSocketHandler  { //More overrides may be needed and can be found in extended class
    
    
    
    //this is temporarily kept here 
    private final int currency = 25000;
    
    private static GameState gameState;
    //List of all currently working clients connected to server
    static List<WebSocketSession> users;
    private ObjectMapper mapper = new ObjectMapper();
    //An object that wraps important gameState pieces
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("New Text Message Received: \n" + message.getPayload());
        
        //De-Serialize message with Jackson
        Command requestedAction = mapper.readValue(message.getPayload(), Command.class);
        
        //Pass serverside fields that commands might need
        requestedAction.injectGameState(gameState);
        requestedAction.injectSession(session);
        
        //Execute command
        requestedAction.execute();
        //prep models for sending
        
        if(!gameState.getChangedObjects().isEmpty() 
                || !gameState.getDestroyed().isEmpty()
                || !gameState.getNewObjects().isEmpty()){
            
            var changeModel = new ChangeModel(gameState);
            
            //Create unique object model for each User
            //TODO
            
            //Serialize model
            String payload = mapper.writeValueAsString(changeModel);
            TextMessage toSend = new TextMessage(payload);
            
            //send message to respective clients
            for(WebSocketSession s : users){ //Currently just broadcasting messages
                s.sendMessage(toSend);
            }
        }
        
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Session left: " + session.getId());
        //Remove Session from users
        System.out.println("size of users before removing player: " + users.size());
        users.remove(session);
        
        System.out.println("size of users after removing player: " + users.size());
        //Remove Sessions Respected player data from gameState
        gameState.removePlayer(session);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Session started: " + session.getId());
        
        
        if(gameState == null){
            gameState = new GameState();
        }
        if(users == null){
            users = new ArrayList<>();
        } 
        //Add new session to users
        users.add(session);
        gameState.addPlayer(session);
        
        
        
    }
}
