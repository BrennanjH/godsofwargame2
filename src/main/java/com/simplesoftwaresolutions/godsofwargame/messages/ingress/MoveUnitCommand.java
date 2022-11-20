/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.ingress;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/** A unit sent here is given a units transform and it's velocity
 * If a change occurs the frontend responsible will send a new moveunitcommand
 *
 * @author brenn
 */
public class MoveUnitCommand implements Command {

    private static final Logger logger= LoggerFactory.getLogger(MoveUnitCommand.class);

    private AbstractUnitObject movingUnit;
    
    private List<PositionalCord> pathingRoute;

    @JsonCreator
    public MoveUnitCommand(@JsonProperty("movingUnit") AbstractUnitObject movingUnit,
                           @JsonProperty("pathingRoute") List<PositionalCord> pathingRoute) {
        this.movingUnit = movingUnit;
        this.pathingRoute = pathingRoute;
    }

    @Override
    public void execute(GameState gameState, WebSocketSession session) {
        try {
            if (movingUnit == null || pathingRoute == null) {
                throw new NullExpectedField(" movingUnit is null or pathingRoute is null");
            }
            //Get profile of command calling player
            PlayerProfile commandCaller = gameState.getPlayerFromSession(session);

            //Find servers instance of movingUnit inside PlayerProfile
            AbstractUnitObject serverSideUnit = null;
            for (AbstractUnitObject playersUnits : commandCaller.getPlayerValues().getUnits()) {
                if (playersUnits.getMeta().compareTo(movingUnit.getMeta())) {
                    serverSideUnit = playersUnits;
                    break;
                }
            }
            //Verify if unit exists
            if (serverSideUnit == null) {
                //TODO - create a custom error message for the session that send this command
            }
            //Change serverSideUnit's route
            serverSideUnit.setRoute(pathingRoute);
        } catch (Exception e ) {
            logger.error(e.getMessage());
        }
    }

    public List<PositionalCord> getPathingRoute() {
        return pathingRoute;
    }

    @Override
    public boolean isBuilt() {
        return movingUnit != null;
    }

    @Override
    public LoadState[] expectedLoadStates() {
        return new LoadState[] {LoadState.FULLY_LOADED} ;
    }


}
