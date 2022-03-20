package com.simplesoftwaresolutions.godsofwargame.player;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerProfileTest {

    @Test
    void getProfileCoalitions(){
        //init env
        //Mocks
        GameState game = mock(GameState.class);
        WebSocketSession session = mock(WebSocketSession.class);
        StringBuilder build = new StringBuilder();

        Coalition coat = new Coalition("TestTeam");
        Team coat2 = mock(Team.class);
        PlayerProfile profile = new PlayerProfile(game, build, session);

        profile.getJoinedTeams().add(coat);
        profile.getJoinedTeams().add(coat);
        profile.getJoinedTeams().add(coat2);

        when(coat2.getType()).thenReturn(Team.class);
        //Action
        List<Coalition> coatList = profile.getProfileCoalitions();
        //Assert
        System.out.println("size: " + coatList.size());
        Assertions.assertTrue(coatList.size() == 2);
    }

    @Test
    void removeCoalitionFromProfile(){
        //init env
        //Mocks
        GameState game = mock(GameState.class);
        WebSocketSession session = mock(WebSocketSession.class);
        StringBuilder build = new StringBuilder();

        Coalition coat = mock(Coalition.class);
        Coalition coat2 = mock(Coalition.class);
        PlayerProfile profile = new PlayerProfile(game, build, session);

        profile.getJoinedTeams().add(coat);
        profile.getJoinedTeams().add(coat2);
        //Action
        PlayerProfile.removeCoalitionFromProfile(coat, profile);
        //Assert
        Assertions.assertTrue(profile.getJoinedTeams().size() == 1);
    }
    @Test
    void removeCoalitionFromProfileNull(){
        //init env
        //Mocks
        GameState game = mock(GameState.class);
        WebSocketSession session = mock(WebSocketSession.class);
        StringBuilder build = new StringBuilder();

        Coalition coat = mock(Coalition.class);

        PlayerProfile profile = new PlayerProfile(game, build, session);
        //Action
        PlayerProfile.removeCoalitionFromProfile(coat, profile);
        //Assert
        Assertions.assertTrue(profile.getJoinedTeams().isEmpty());
    }


}