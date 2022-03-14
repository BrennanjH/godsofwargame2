/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.ChangeModel;
import com.simplesoftwaresolutions.godsofwargame.messages.services.CommunicationService;
import com.simplesoftwaresolutions.godsofwargame.messages.services.MessageService;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** The websocket handler that handles how messages are handled in different situations such as joining, leaving, and talking
 *
 * @author brenn
 */

public class WebSocketHandling extends AbstractWebSocketHandler  { //More overrides may be needed and can be found in extended class


    @Autowired
    public WebSocketHandling(GameState gameState){
        System.out.println("WebsocketHandling constructor entered");
        this.gameState = gameState;
        messageService = new MessageService();
    }

    /**
     * A Communication service that will handle the message built and verify its integrity
     */
    private CommunicationService messageService;

    //A wrapper on integral game related objects
    private static GameState gameState;
    //List of all currently working clients connected to server
    static List<WebSocketSession> users;
    //Json handler
    private ObjectMapper mapper;
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("New Text Message Received: \n" + message.getPayload());
        
        //De-Serialize message with Jackson
        Command requestedAction = mapper.readValue(message.getPayload(), Command.class);

        //Execute command inside command service
        //messageService.setCommand(requestedAction);
        messageService.handleCommand(gameState, session, requestedAction);
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
        users.remove(session);
        
        //Remove Sessions Respected player data from gameState
        gameState.removePlayer(session);
        
        
        //Update players on lost units
        for(WebSocketSession s : users){
            //build model
            var changeModel = new ChangeModel(gameState);
            
            //Serialize model
            String payload = mapper.writeValueAsString(changeModel);
            TextMessage toSend = new TextMessage(payload);
            //send model to users
            s.sendMessage(toSend);
        }
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Session started: " + session.getId());
        init();


        //Set up player data
        gameState.addPlayer(session);

        //Check if session is first joiner and if so make them host of Lobby
        if(users.isEmpty() && gameState.loadState == LoadState.LOBBY){
            gameState.getPlayerFromSession(session).serverRole = ServerRole.LOBBY_HOST;
        }
        //Add new session to users
        users.add(session);
        //System.out.println("Agones address " + agones.gameServer().getStatus().getAddress() );
    }


    /**
     * Initiates the fields present in WebSocketHandling that have not yet initialized
     */
    private void init(){
        /*
        if(gameState == null){
            gameState = new GameState();
        }

         */
        if(users == null){
            users = new ArrayList<>();
        }
        if(mapper == null){
            mapper = new ObjectMapper();//.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }
}
