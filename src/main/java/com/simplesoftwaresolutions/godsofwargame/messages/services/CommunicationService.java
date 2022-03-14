package com.simplesoftwaresolutions.godsofwargame.messages.services;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import org.springframework.web.socket.WebSocketSession;

public interface CommunicationService {
    /** A method that handles the execution of a Command object
     * @return true is returned if command succesfully builds and runs
     * @param gameState the game State object currently in use
     * @param session The session of the user who sent the command
     */
    public boolean handleCommand(GameState gameState, WebSocketSession session, Command command);

}
