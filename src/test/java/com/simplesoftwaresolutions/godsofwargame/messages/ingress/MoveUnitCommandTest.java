package com.simplesoftwaresolutions.godsofwargame.messages.ingress;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.InstanceId;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.units.HeavyTrackMovementPlatform;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MoveUnitCommandTest {

    @Test
    void execute() {
        //Set up env
        //SetUpPlayer
        WebSocketSession session =  mock(WebSocketSession.class);
        when(session.getId()).thenReturn("player00SessionId");

        GameState gs = new GameState(null);
        gs.addPlayer(session);
        InstanceId id = new InstanceId("instanceId000");
        id.setOwnerNickName(gs.getNickName(session));

        gs.getPlayerFromSession(session).getPlayerValues().getUnits().add(new StandardUnit(new HeavyTrackMovementPlatform(),
                null,
                id,
                null)); //Add new unit that will be moved


        //SetupCommand

        List<PositionalCord> coordTest = new ArrayList<>();
        coordTest.add(new PositionalCord(1,1));
        coordTest.add(new PositionalCord(2,2));

        InstanceId passedInstance = new InstanceId("instanceId000");
        passedInstance.setOwnerNickName(new StringBuilder("player00SessionId"));

        MoveUnitCommand command = new MoveUnitCommand(
                new StandardUnit(null,null, passedInstance,null),
                coordTest);


        //Action
        try {
            command.execute(gs, session);
        } catch (NullExpectedField e) {
            e.printStackTrace();
        }
        //Assert
        Assertions.assertTrue(gs.getPlayerFromSession(session).getPlayerValues().getUnits().get(0).getPath().get(0).getX() == 1);
    }
}