package com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.Board;
import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.BrennansFolly;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.Map;
import com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages.ChangeMapCommand;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeMapCommandTest {

    GameState gs;
    ChangeMapCommand command;
    WebSocketSession session;

    @BeforeEach
    void setUp(){
        //Create gameState object
        gs = new GameState(new BoardManager(new Board()));

        //Create a map command with a valid map
        Map map = new BrennansFolly();
        command = new ChangeMapCommand(map);

        //Add player to gamestate
        session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("WSID");
        gs.addPlayer(session);

        //Assign player a role
        gs.getPlayerFromSession(session).setServerRole(ServerRole.LOBBY_HOST);
    }

    @Test
    void execute() {
        //env

        //exec
        try {
            command.execute(gs,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //assert
        Assertions.assertTrue(
                gs.getBoardManager().getTerrainLists().size() == 10 &&
                        gs.getBoardManager().getTerrainLists().get(0).size() == 15);
        //List created by map isn't accesible by test so instead we look to verify that the size is as expected
    }

    @Test
    void executeThrown() {
        //env
        command = new ChangeMapCommand(null);

        //assert & exec
        Assertions.assertThrows(NullExpectedField.class, () -> command.execute(gs,session));
    }

    @Test
    void executeInvalidUser() {
        //env

        gs.getPlayerFromSession(session).setServerRole(ServerRole.LOBBY_MEMBER);
        //exec
        try {
            command.execute(gs,session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //assert
        Assertions.assertNull(gs.getBoardManager().getTerrainLists());
    }

    @Test
    void isBuilt() {
        //env

        //exec

        //assert
    }

    @Test
    void expectedLoadStates() {
        //env

        //exec & assert
        LoadState[] ls = command.expectedLoadStates();
        //Assert
        Assertions.assertTrue(ls.length == 1);
        Assertions.assertTrue(ls[0] == LoadState.LOBBY);

    }
}