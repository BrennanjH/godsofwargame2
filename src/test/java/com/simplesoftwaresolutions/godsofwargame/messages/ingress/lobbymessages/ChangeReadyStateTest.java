package com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages.ChangeReadyState;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeReadyStateTest {

    @Test
    void execute() {
        ChangeReadyState command = new ChangeReadyState(true);
        GameState gameState = new GameState();
        WebSocketSession session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("TESTID");

        gameState.addPlayer(session);

        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(gameState.getPlayerFromSession(session).getPlayerValues().isReadyState());
    }


    @Test
    void executeGameStarted() {
        ChangeReadyState command = new ChangeReadyState(false);
        GameState gameState = new GameState();
        WebSocketSession session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("TESTID");

        gameState.addPlayer(session);
        gameState.getPlayerFromSession(session).getPlayerValues().setReadyState(true);
        gameState.loadState = LoadState.FULLY_LOADED;
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(gameState.getPlayerFromSession(session).getPlayerValues().isReadyState());
    }

}