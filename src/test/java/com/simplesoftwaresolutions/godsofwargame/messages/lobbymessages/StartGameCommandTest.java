package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StartGameCommandTest {
    WebSocketSession session;
    GameState gameState;
    @BeforeEach
    void buildEnv(){
        //Create gameState and player
        gameState = new GameState();
        session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("TESTID");

        //add Player to gamestate
        gameState.addPlayer(session);

    }
    @Test
    void executeAllReady()  {
        gameState.loadState = LoadState.LOBBY;
        gameState.getPlayerFromSession(session).setServerRole(ServerRole.LOBBY_HOST);
        gameState.getPlayerFromSession(session).getPlayerValues().setReadyState(true);
        StartGameCommand command = new StartGameCommand();

        try {
            command.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(gameState.loadState == LoadState.PRE_GAME);
    }

    @Test
    void executeForceStart(){
        //Create a second player that isn't ready
        WebSocketSession session1 = mock(WebSocketSession.class);
        when(session1.getId()).thenReturn("TESTID-1");
        gameState.addPlayer(session1);

        //Set up lobby host and gameState
        gameState.loadState = LoadState.LOBBY;
        gameState.getPlayerFromSession(session).setServerRole(ServerRole.LOBBY_HOST);
        gameState.getPlayerFromSession(session).getPlayerValues().setReadyState(true);
        //Create tested command object
        StartGameCommand command = new StartGameCommand();

        try {
            command.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertFalse(gameState.getTasks().isEmpty());
    }

    @Test
    void executeInvalidUser(){
        //Set up lobby host and gameState
        gameState.loadState = LoadState.LOBBY;
        gameState.getPlayerFromSession(session).setServerRole(ServerRole.LOBBY_MEMBER);
        gameState.getPlayerFromSession(session).getPlayerValues().setReadyState(true);
        //Create tested command object
        StartGameCommand command = new StartGameCommand();

        try {
            command.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(gameState.getPlayerFromSession(session).getServerRole() == ServerRole.LOBBY_MEMBER);
    }

}