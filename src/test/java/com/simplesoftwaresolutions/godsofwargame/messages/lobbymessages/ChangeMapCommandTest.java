package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeMapCommandTest {

    @Test
    void execute() {
        ChangeMapCommand command = new ChangeMapCommand("NewMap");
        WebSocketSession session = mock(WebSocketSession.class);
        GameState gameState = new GameState();
        when(session.getId()).thenReturn("TESTID");

        gameState.addPlayer(session);
        gameState.getPlayerFromSession(session).serverRole = ServerRole.LOBBY_HOST;
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(gameState.getMap().getMapName().compareTo("NewMap") == 0);
    }
    @Test
    void executeGameStarted() {
        ChangeMapCommand command = new ChangeMapCommand("NewMap");
        WebSocketSession session = mock(WebSocketSession.class);
        GameState gameState = new GameState();
        when(session.getId()).thenReturn("TESTID");

        gameState.addPlayer(session);
        gameState.getPlayerFromSession(session).serverRole = ServerRole.LOBBY_HOST;

        gameState.loadState = LoadState.FULLY_LOADED;
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertFalse(gameState.getMap().getMapName().compareTo("NewMap") == 0);
    }
    @Test
    void executeNotHost() {
        ChangeMapCommand command = new ChangeMapCommand("NewMap");
        WebSocketSession session = mock(WebSocketSession.class);
        GameState gameState = new GameState();
        when(session.getId()).thenReturn("TESTID");

        gameState.addPlayer(session);
        gameState.getPlayerFromSession(session).serverRole = ServerRole.LOBBY_MEMBER;

        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertFalse(gameState.getMap().getMapName().compareTo("NewMap") == 0);
    }

}