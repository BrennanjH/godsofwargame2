package com.simplesoftwaresolutions.godsofwargame.messages.services;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.springframework.web.socket.WebSocketSession;

public class MessageService implements CommunicationService{

    private Command command;

    private GameState gameState;

    public MessageService(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean handleCommand(GameState gameState, WebSocketSession session) {
        //Verify that command is usable by
        if( isExpectedLoadStateInArray(command.expectedLoadStates(),this.gameState.loadState) ) {

            try {
                command.execute(gameState,session);
            } catch (NullExpectedField e) {
                System.err.println("NullExpectedField thrown, Caught inside MessageService" +
                        "\n Command: " + command.getClass().toString());
                return false;
            }
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

    public Command getCommand() {
        return command;
    }

    @Override
    public void setCommand(Command command) {
        this.command = command;
    }

}
