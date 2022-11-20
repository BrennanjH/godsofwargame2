package com.simplesoftwaresolutions.godsofwargame.messages.services;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * an object capable of handling incoming commands from clients
 */
@Component
public class MessageService implements CommunicationService{

    private static final Logger logger= LoggerFactory.getLogger(MessageService.class);
    @Override
    public boolean handleCommand(GameState gameState, WebSocketSession session, Command command) {
        //Verify that command is usable by
        if( isExpectedLoadStateInArray(command.expectedLoadStates(),gameState.loadState) ) {

            command.execute(gameState,session);

            return true;
        }
        return false;
    }

    private boolean isExpectedLoadStateInArray(LoadState[] validLoadState, LoadState expectedLoadState) {
        for(LoadState l : validLoadState){
            if(l == expectedLoadState)
                return true;
        }
        return false;
    }

}
