package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeNickNameCommandTest {
    static GameState gameState ;
    static WebSocketSession session ;

    @BeforeEach
    void beforeEach(){

        gameState = new GameState();
        session = mock(WebSocketSession.class);
        //Start env
        when(session.getId()).thenReturn("WSID");
        gameState.addPlayer(session);

        gameState.loadState = LoadState.LOBBY; //Make sure loadState is PreGame
    }
    @Test
    void executeFullyLoaded(){
        //Init env
        ChangeNickNameCommand command = new ChangeNickNameCommand("NewNickName");
        gameState.loadState = LoadState.FULLY_LOADED;

        //Action
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert
        Assertions.assertFalse( gameState.getNickNames().get("WSID").compareTo(new StringBuilder("NewNickName")) == 0);

    }
    @Test
    void execute() {
        //Init Env
        Command command = new ChangeNickNameCommand("NewNickName");
        gameState.loadState = LoadState.LOBBY;
        //Action
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert : Looking for the nickname saved for userID WSID to be equal to NewNickName
        Assertions.assertTrue( gameState.getNickNames().get("WSID").compareTo(new StringBuilder("NewNickName")) == 0);
    }
    @Test
    void executeExceptions(){
        //Init Env
        Command command = new ChangeNickNameCommand(null);
        gameState.loadState = LoadState.LOBBY;

        //Test Exception
        Assertions.assertThrows(NullExpectedField.class , () -> { command.execute(gameState, session);});
    }
}