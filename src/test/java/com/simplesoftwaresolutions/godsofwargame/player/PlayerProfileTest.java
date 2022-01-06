package com.simplesoftwaresolutions.godsofwargame.player;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;

class PlayerProfileTest {

    @Test
    void addToDestroyingQueue() {
        //Create environment
        GameState testGameState =  new GameState();
        PlayerProfile testPlayerProfile = new PlayerProfile(testGameState,  new StringBuilder(), mock(WebSocketSession.class));

        //Perform Method
        testPlayerProfile.addToDestroyingQueue(testGameState);

        //Assert correct response
        Assertions.assertFalse(testGameState.getDestroyed().isEmpty());
        Assertions.assertEquals(1, testGameState.getDestroyed().size());

    }

    @Test
    void addToNewObjectsQueue() {
        //Create environment
        GameState testGameState =  new GameState();
        PlayerProfile testPlayerProfile = new PlayerProfile(testGameState,  new StringBuilder(), mock(WebSocketSession.class));

        //Perform Method
        testPlayerProfile.addToNewObjectsQueue(testGameState);

        //Assert correct response
        Assertions.assertFalse(testGameState.getNewObjects().isEmpty());
        Assertions.assertEquals(1, testGameState.getNewObjects().size());
    }

    @Test
    void addToSerializationQueue() {
        //Create environment
        GameState testGameState =  new GameState();
        PlayerProfile testPlayerProfile = new PlayerProfile(testGameState, new StringBuilder(), mock(WebSocketSession.class));

        //Perform Method
        testPlayerProfile.addToChangeableQueue(testGameState);

        //Assert correct response
        Assertions.assertFalse(testGameState.getChangedObjects().isEmpty());
        Assertions.assertEquals(1, testGameState.getChangedObjects().size());
    }
}