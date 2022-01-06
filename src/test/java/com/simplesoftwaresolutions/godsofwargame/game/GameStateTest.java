package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameStateTest {

    @Test
    void addPlayer() {
        //Init Environment
        GameState testGameState = new GameState();
        WebSocketSession session = mock(WebSocketSession.class);
        //stub id
        when(session.getId()).thenReturn("WSID");
        //Action
        testGameState.addPlayer(session);
        //Assert
        //Assert NickName
        Assertions.assertEquals("WSID", testGameState.getNickName(session).toString());
        //Assert PlayerData
        Assertions.assertNotNull(testGameState.getPlayerData().get(testGameState.getNickName(session)));
        //Assert Queue Change
        Assertions.assertFalse(testGameState.getNewObjects().isEmpty());
    }

    @Test
    void changeNickName() {
        //Init Environment
        GameState testGameState = new GameState();
        WebSocketSession test = mock(WebSocketSession.class);
        //Stub ID
        when(test.getId()).thenReturn("WSID");
        testGameState.addPlayer(test);

        //Action
        testGameState.changeNickName( test , "TestedNickName");

        //Assertions
        Assertions.assertEquals("TestedNickName", testGameState.getNickName(test).toString());
    }

    @Test
    void removePlayer() {
        //Init env
        GameState gameState = new GameState();
        WebSocketSession session = mock(WebSocketSession.class);
        //Stub ID
        when(session.getId()).thenReturn("WSID");
        //A player object is needed for proper testing
        gameState.addPlayer(session);

        //Action
        gameState.removePlayer(session);
        //Assertions
        //Check if player data has been removed from list
        Assertions.assertTrue(gameState.getPlayerData().isEmpty());
        //check if players nickname has been fully removed
        Assertions.assertTrue(gameState.getNickNames().isEmpty());
        //Check if player data is queued for removal
        Assertions.assertFalse(gameState.getDestroyed().isEmpty());

    }



    @Test
    void addChangeableToQueue(){
        //Generate Environment
        GameState testGameState = new GameState();
        Changeable mockPlayerProfile = mock(PlayerProfile.class);

        //Perform Tested Method
        testGameState.addChangeableToQueue(mockPlayerProfile);

        //Assert correct response
        Assertions.assertFalse(testGameState.getChangedObjects().isEmpty());
        Assertions.assertEquals(1, testGameState.getChangedObjects().size());
    }
    @Test
    void addCreatableToQueue(){
        //Generate Environment
        GameState testGameState = new GameState();
        Createable mockPlayerProfile =  mock(PlayerProfile.class);

        //Perform Tested Method
        testGameState.addCreatableToQueue(mockPlayerProfile);

        //Assert correct response
        Assertions.assertFalse(testGameState.getNewObjects().isEmpty());
        Assertions.assertEquals(1, testGameState.getNewObjects().size());
    }

    @Test
    void addDestroyableToQueue(){
        //Generate Environment
        GameState testGameState = new GameState();
        Destroyable mockPlayerProfile =  mock(PlayerProfile.class);

        //Perform Tested Method
        testGameState.addDestroyableToQueue(mockPlayerProfile );

        //Assert
        Assertions.assertFalse(testGameState.getDestroyed().isEmpty());
        Assertions.assertEquals(1, testGameState.getDestroyed().size());

    }
}