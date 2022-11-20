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
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.InstanceIdMistmatchException;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;


/** A command that adds a new unit to the game and then properly informs players of it
 * Unit's implement their own createSelf() which will handle creation so only validation
 * is performed by this command
 * @author brenn
 */
public class CreateStandardUnitCommand implements Command {

    private static final Logger logger= LoggerFactory.getLogger(CreateStandardUnitCommand.class);
    
    private StandardUnit unit;
    
    @JsonCreator
    public CreateStandardUnitCommand(@JsonProperty("unit")StandardUnit unit){
        this.unit = unit;
    }
    
    @Override
    public synchronized void execute(GameState gameState, WebSocketSession session) {
        try {
            //verify that all required pieces of information are present in the unit recieved from the front end
            if (!unit.isBuilt()) {
                logger.error("Command CreateUnit Failed To Create Unit");
                throw new NullExpectedField("Unit failed to build");
            }

            StringBuilder commandMaker = gameState.getNickNames().get(session.getId());
            //Make sure the Command Issuer is the same person as the units Owner
            if (commandMaker.toString().compareTo(unit.getOwnerNickName()) == 0) {
                logger.info("CreateStandardUnitCommand: Execute(): conditional passed");

                //Compare given instanceId from unit with playerlist of units to verify that no existing unit has given id (for this player)
                verifyInstanceIds(gameState, session);

                //Inject Dependents
                unit.setGameState(gameState);

                //Set the Unit's string builder to the sessions
                unit.setOwnerNickName(commandMaker);

                //Allow the Unit to handle its own creation
                unit.createSelf(gameState.getPlayerData().get(commandMaker));
            } else {
                logger.warn("Player requested unit with Owner: " + unit.getOwnerNickName() + " when command requester nickname is: " + commandMaker);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void verifyInstanceIds(GameState gameState, WebSocketSession session) throws InstanceIdMistmatchException {
        List<AbstractUnitObject> playerUnits = gameState.getPlayerFromSession(session).getPlayerValues().getUnits();
        for (AbstractUnitObject compareTo :
                playerUnits) {
            if(unit.getInstanceId().compareTo(compareTo.getInstanceId()) == 0){
                throw new InstanceIdMistmatchException("Instance Id: " + unit.getInstanceId() + " already exists");
            }
        }
    }

    @Override
    public boolean isBuilt() {
        return (unit != null);
    }

    @Override
    public LoadState[] expectedLoadStates() {
        return new LoadState[] {LoadState.PRE_GAME, LoadState.FULLY_LOADED};
    }

    public AbstractUnitObject getUnit() {
        return unit;
    }

    
}
