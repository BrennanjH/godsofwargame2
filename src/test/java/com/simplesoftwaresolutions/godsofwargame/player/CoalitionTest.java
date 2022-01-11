package com.simplesoftwaresolutions.godsofwargame.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class CoalitionTest {

    @Test
    void removeProfileFromCoalition(){
        //Init Env
        Coalition coat = new Coalition("TestCoalition");
        PlayerProfile profile = mock(PlayerProfile.class);
        PlayerProfile profile2 = mock(PlayerProfile.class);
        coat.getPlayersOnTeam().add(profile);
        coat.getPlayersOnTeam().add(profile2);

        Assertions.assertFalse(coat.getPlayersOnTeam().isEmpty()); //Just making sure add works
        //Action
        Coalition.removeProfileFromCoalition(coat, profile);
        //Assert
        Assertions.assertTrue(coat.getPlayersOnTeam().size() == 1);
    }
    @Test
    void removeProfileFromCoalitionNull(){
        //Init Env
        Coalition coat = new Coalition("TestCoalition");
        PlayerProfile profile = mock(PlayerProfile.class);
        //Action
        Coalition.removeProfileFromCoalition(coat, profile);

        //Assert
        Assertions.assertTrue(coat.getPlayersOnTeam().isEmpty());
    }
}