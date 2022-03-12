package com.simplesoftwaresolutions.godsofwargame.messages;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import com.simplesoftwaresolutions.godsofwargame.units.AntiAirGunTurretPlatform;
import com.simplesoftwaresolutions.godsofwargame.units.LightTrackMovementPlatform;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateUnitCommandTest {
    static GameState gameState ;
    static WebSocketSession session ;
    @BeforeAll
    static void instantiate(){
        gameState = new GameState();
        session = mock(WebSocketSession.class);
        //Start env
        when(session.getId()).thenReturn("WSID");
        gameState.addPlayer(session);
    }
    @Test
    void execute() {
        //Init Env
        AbstractUnitObject unit = new StandardUnit(new LightTrackMovementPlatform()
                ,new AntiAirGunTurretPlatform()
                ,new InstanceId());
        Command command = new CreateUnitCommand(unit);


        unit.meta = new InstanceId();
        //Action
        unit.meta.setOwnerNickName(new StringBuilder(" "));
        try {
            command.execute(gameState,session);
        } catch (NullExpectedField nullExpectedField) {
            nullExpectedField.printStackTrace();
        }
        //Assert
        Assertions.assertTrue(unit.getGameState() == null);

    }
    @Test
    void executeRealUser() {
        //Init Env
        AbstractUnitObject unit = new StandardUnit(new LightTrackMovementPlatform()
                ,new AntiAirGunTurretPlatform()
                ,new InstanceId());
        Command command = new CreateUnitCommand(unit);


        unit.meta = new InstanceId();
        //Action
        unit.meta.setOwnerNickName(new StringBuilder("WSID"));
        try {
            command.execute(gameState,session);
        } catch (NullExpectedField nullExpectedField) {
            nullExpectedField.printStackTrace();
        }
        //Assert
        Assertions.assertFalse(unit.getGameState() == null);

    }


    @Test
    void executeThrowable(){
        //Init Env
        AbstractUnitObject unit = new StandardUnit();
        Command command = new CreateUnitCommand(unit);


        Assertions.assertThrows(NullExpectedField.class , () -> {
            command.execute(gameState, session);
        });
    }
}