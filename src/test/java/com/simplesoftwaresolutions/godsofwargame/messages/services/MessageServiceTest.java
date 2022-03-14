package com.simplesoftwaresolutions.godsofwargame.messages.services;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageServiceTest {

    GameState gs;
    WebSocketSession session;
    Command command;
    MessageService ms;

    @BeforeEach
    void setup(){

        gs = new GameState();
        gs.loadState = LoadState.LOBBY;

        session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("WSID");


        ms = new MessageService();
    }


    @Test
    void handleCommand() {
        //env
        command = mock(Command.class);
        when(command.expectedLoadStates()).thenReturn(new LoadState[] {LoadState.LOBBY});
        //exert
        boolean result = ms.handleCommand(gs,session,command);
        //assert
        Assertions.assertTrue(result);
    }
    @Test
    void handleCommandInvalidCommandState() {
        //env
        command = mock(Command.class);
        when(command.expectedLoadStates()).thenReturn(new LoadState[] {LoadState.PRE_GAME});
        //exert
        boolean result = ms.handleCommand(gs,session,command);
        //assert
        Assertions.assertFalse(result);
    }
    @Test
    void handleCommandGS() {
        //env
        command = mock(Command.class);
        when(command.expectedLoadStates()).thenReturn(new LoadState[] {LoadState.LOBBY});
        try {
            Mockito.doThrow(new NullExpectedField())
                    .when(command)
                    .execute(gs,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //exert
        boolean result = ms.handleCommand(gs,session,command);
        //assert
        Assertions.assertFalse(result);
    }
    @Test
    void handleCommandInvalidGS() {
        //env
        command = mock(Command.class);
        gs.loadState = LoadState.FULLY_LOADED;
        when(command.expectedLoadStates()).thenReturn(new LoadState[] {LoadState.LOBBY});
        //exert
        boolean result = ms.handleCommand(gs,session,command);
        //assert
        Assertions.assertFalse(result);
    }
}