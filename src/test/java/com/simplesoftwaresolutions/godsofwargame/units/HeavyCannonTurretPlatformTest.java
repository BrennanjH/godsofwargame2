package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeavyCannonTurretPlatformTest {

    PlayerProfile player1;
    PlayerProfile player2;

    //Attacking unit
    AbstractUnitObject ownsTurret;
    AbstractTurretPlatform heavyTurret;
    InstanceId id1;
    FullPositionalCord coord1;

    //Targeting Unit
    AbstractUnitObject ownedByPlayer2;
    InstanceId id2;
    FullPositionalCord coord2;


    @BeforeEach
    void setUp() {
        //set up ids
        id1 = new InstanceId();
        id2 = new InstanceId();
        id1.setOwnerNickName(new StringBuilder("player1 id"));
        id2.setOwnerNickName(new StringBuilder("player2 id"));

        //set up coords
        coord1 = new FullPositionalCord();
        coord2 = new FullPositionalCord();
        coord1.setX(5);
        coord1.setY(5);

        coord2.setX(4);
        coord2.setY(4);

        //create units
        ownsTurret = new StandardUnit(null, heavyTurret, id1, coord1);
        ownedByPlayer2 = new StandardUnit(null,null, id2, coord2);

        //Set up mocks
        WebSocketSession sess1 = mock(WebSocketSession.class);
        WebSocketSession sess2 = mock(WebSocketSession.class);
        when(sess1.getId()).thenReturn("sess1 session id");
        when(sess2.getId()).thenReturn("sess2 session id");

        //Set up player profiles
        player1 = new PlayerProfile(null, id1.getOwnerNickName(), sess1);
        player2 = new PlayerProfile(null, id2.getOwnerNickName() ,sess2);

        //give units to players
        player1.getPlayerValues().getUnits().add(ownsTurret);
        player2.getPlayerValues().getUnits().add(ownedByPlayer2);


    }

    @Test
    void attack() {

    }

    @Test
    void isBuilt() {
    }

    @Test
    void findTarget() {
        //env
        //Create a heavy turret suitable for test
        heavyTurret = new HeavyCannonTurretPlatform(-1,-1,5);
        ownsTurret.turretPlatform = heavyTurret;

        //Add players to list
        List<PlayerProfile> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        //action
        ownsTurret.turretPlatform.findTarget(playerList, ownsTurret);
        //assert
        Assertions.assertTrue(ownsTurret.getTurretPlatform().getTarget().getTargetedUnit() == ownedByPlayer2);
    }
    @Test
    void findTargetEdgeCase() {
        //env
        //Create a heavy turret suitable for test
        int targetRange = 5;
        heavyTurret = new HeavyCannonTurretPlatform(-1,-1,targetRange);
        ownsTurret.turretPlatform = heavyTurret;
        coord2.setX(coord1.getX() - targetRange); //set x to be the exact range of the targets capability
        coord2.setY(coord1.getY());
        //Add players to list
        List<PlayerProfile> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        //action
        ownsTurret.turretPlatform.findTarget(playerList, ownsTurret);
        //assert
        Assertions.assertTrue(ownsTurret.getTurretPlatform().getTarget().getTargetedUnit() == ownedByPlayer2);
    }
    @Test
    void priceOfSelf() {
    }
}