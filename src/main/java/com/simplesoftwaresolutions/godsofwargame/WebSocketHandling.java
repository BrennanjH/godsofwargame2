/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
    
    //List of all currently working clients connected to server
    static List<WebSocketSession> users;
    
    //An object that wraps important gameState pieces
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("New Text Message Received");
        //De-Serialize message with Jackson
        ObjectMapper mapper = new ObjectMapper();
        Command requestedAction = mapper.readValue(message.getPayload(), Command.class);
        //Execute command
        requestedAction.execute();
        //Create Change model for each user
        //TODO 
        //Serialize Change model list
        //TODO 
        //Send out change model to respective users
        //TODO 
        
        session.sendMessage(message);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Session left: " + session.getId());
        //Remove Session from users
        System.out.println("size of users before removing player: " + users.size());
        users.remove(session);
        
        System.out.println("size of users after removing player: " + users.size());
        //Remove Sessions Respected player data from gameState
        
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Session started: " + session.getId());
        //Instantiate session list for message handling, and Spring object factory
        if(users == null){
            //Instantiate as an arrayList
            users = new ArrayList<>();
            
        } else {
            //Add new session to users
            users.add(session);
            //Create new playerData object for user
            PlayerData newPlayer = new PlayerData(currency, session);
            
            //Add playerData to gameState
            //TODO 
        }
        
    }
}
