package com.simplesoftwaresolutions.godsofwargame.messages.lobbymessages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.Coalition;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeCoalitionCommandTest {
    static ChangeCoalitionCommand command;
    static GameState gameState;
    static WebSocketSession session;
    static Coalition coat;
    @BeforeEach
    void beforeEach(){
        command = new ChangeCoalitionCommand("TestTeamName");
        gameState = new GameState();

        session = mock(WebSocketSession.class);
        when(session.getId()).thenReturn("TestId");

        coat = new Coalition("TestTeamName");

        gameState.addPlayer(session);
    }
    @Test
    void executeCheckGameState() {
        //init Env
        PlayerProfile requestSenderProfile = gameState.getPlayerData()
                .get(gameState.getNickName(session));
        gameState.loadState = LoadState.FULLY_LOADED;
        //Action
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert
        Assertions.assertTrue(requestSenderProfile.getJoinedTeams().isEmpty());
    }
    @Test
    void executeThrowables() {
        //init Env

        ChangeCoalitionCommand command = new ChangeCoalitionCommand(null);
        //Action & Assert
        Assertions.assertThrows(NullExpectedField.class, () -> {command.execute(gameState, session);} );

    }
    @Test
    void executeTeamDoesntExist() {
        //init Env
        PlayerProfile requestSenderProfile = gameState.getPlayerData()
                .get(gameState.getNickName(session));
        ChangeCoalitionCommand command = new ChangeCoalitionCommand("TeamDidn'tExist");
        //Action
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert
        Assertions.assertFalse(requestSenderProfile.getProfileCoalitions().get(0).getTeamName().compareTo("TestTeamName") == 0 ) ;
    }
    @Test
    void executeTeamDoesExist() {
        //init Env
        PlayerProfile requestSenderProfile = gameState.getPlayerData()
                .get(gameState.getNickName(session));

        //Action
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert
        Assertions.assertTrue(requestSenderProfile.getProfileCoalitions().get(0).getTeamName().compareTo("TestTeamName") == 0 ) ;

    }
    @Test
    void executePlayerOnTeam() {
        //init Env
        PlayerProfile requestSenderProfile = gameState.getPlayerData()
                .get(gameState.getNickName(session));
        requestSenderProfile.getProfileCoalitions().add(new Coalition("FakeTeamName"));
        //Action
        try {
            command.execute(gameState, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert
        Assertions.assertTrue(requestSenderProfile.getProfileCoalitions().get(0).getTeamName().compareTo("TestTeamName") == 0 ) ;
        Assertions.assertFalse(requestSenderProfile.getProfileCoalitions().size() > 1);
    }




}