package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancelStartCommandTest {
    WebSocketSession session;
    GameState gameState;
    @BeforeEach
    void buildEnv(){
        //Create gameState and player
        gameState = new GameState(null);
        session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("TESTID");

        //add Player to gamestate
        gameState.addPlayer(session);
        gameState.getPlayerFromSession(session).serverRole = ServerRole.LOBBY_HOST;

    }

    @Test
    void execute() {
        //A start command is the easiest way to make sure the cancel command works
        StartGameCommand start = new StartGameCommand();
        try {
            start.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        CancelStartCommand command = new CancelStartCommand();

        try {
            command.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(gameState.getTasks().isEmpty());
    }

    @Test
    void executeNotScheduled() {
        CancelStartCommand command = new CancelStartCommand();

        try {
            command.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(gameState.getTasks().isEmpty());
    }

    @Test
    void executeNonValidExecution() {
        //A start command is the easiest way to make sure the cancel command works
        StartGameCommand start = new StartGameCommand();
        try {
            start.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //remove the role of Lobby host
        gameState.getPlayerFromSession(session).serverRole = ServerRole.GAME_MEMBER;

        //Now have a non lobby host execute command
        CancelStartCommand command = new CancelStartCommand();

        try {
            command.execute(gameState,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(gameState.getTasks().isEmpty());
    }

}