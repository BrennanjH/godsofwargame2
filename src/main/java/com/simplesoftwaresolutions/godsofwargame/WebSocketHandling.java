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
import com.simplesoftwaresolutions.godsofwargame.messages.egress.AbstractReturnModel;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.StandardPayload;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.messages.services.CommunicationService;
import com.simplesoftwaresolutions.godsofwargame.messages.services.PlayerViewService;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** The websocket handler that handles how messages are handled in different situations such as joining, leaving, and talking
 *
 * @author brenn
 */
@Component
public class WebSocketHandling extends AbstractWebSocketHandler  { //More overrides may be needed and can be found in extended class

    private static final Logger logger= LoggerFactory.getLogger(WebSocketHandling.class);
    @Autowired PlayerViewService viewService;

    //A wrapper on integral game related objects
    @Autowired GameState gameState;

    @Autowired CommunicationService messageService;

    //A message service that tracks noteworthy changes in the code
    DataServiceBus dsb = DataServiceBus.getInstance();

    //List of all currently working clients connected to server
    static List<WebSocketSession> users;
    //Json handler
    private ObjectMapper mapper;
    
    @Override
    protected synchronized void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        logger.info("New Text Message Received: \n" + message.getPayload());

        //De-Serialize message with Jackson
        Command requestedAction = mapper.readValue(message.getPayload(), Command.class);

        //Execute command inside command service
        try {
            messageService.handleCommand(gameState, session, requestedAction);
        } catch (Exception e) {
            logger.error("error occurred while trying to handle command");
            logger.error(e.getMessage());
        }

        //prep models for sending
        if(!dsb.getChangeables().isEmpty()
                || !dsb.getDestroyables().isEmpty()
                || !dsb.getCreatables().isEmpty()){
            
            StandardPayload changeModel = new StandardPayload(dsb);
            
            //Create unique object model for each User
            Map<String, AbstractReturnModel> clientViews = viewService.createUniqueMessageForEachClient(changeModel, users);

            //send message to respective clients
            for(WebSocketSession s : users){
                //Serialize model
                String payload = mapper.writeValueAsString(clientViews.get(s.getId()));
                TextMessage toSend = new TextMessage(payload);
                //send model to respective player
                s.sendMessage(toSend);
            }
        }
        
    }
    
    @Override
    public synchronized void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Session left: " + session.getId());
        
        //Remove Session from users
        users.remove(session);
        
        //Remove Sessions Respected player data from gameState
        gameState.removePlayer(session);


        //Update players on lost units
        for(WebSocketSession s : users){
            //build model
            StandardPayload changeModel = new StandardPayload(dsb);
            
            //Serialize model
            String payload = mapper.writeValueAsString(changeModel);
            TextMessage toSend = new TextMessage(payload);
            //send model to users
            s.sendMessage(toSend);
        }
        //Check if lost player was last connection
        if(gameState.getPlayerData().isEmpty()){
            gameState.returnToLobbyState();
        }
    }
    
    @Override
    public synchronized void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Session started: " + session.getId());
        init();


        //Set up player data
        gameState.addPlayer(session);

        //Check if session is first joiner and if so make them host of Lobby
        if(users.isEmpty() && gameState.loadState == LoadState.LOBBY){
            gameState.getPlayerFromSession(session).setServerRole(ServerRole.LOBBY_HOST);
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
