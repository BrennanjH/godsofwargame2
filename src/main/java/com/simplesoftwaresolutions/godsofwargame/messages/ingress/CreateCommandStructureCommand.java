package com.simplesoftwaresolutions.godsofwargame.messages.ingress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.units.CommandStructure;
import org.springframework.web.socket.WebSocketSession;

public class CreateCommandStructureCommand implements Command {

    private CommandStructure commandStructure;

    public CreateCommandStructureCommand(@JsonProperty("commandStructure") CommandStructure commandStructure) {
        this.commandStructure = commandStructure;
    }


    @Override
    public void execute(GameState gameState, WebSocketSession session) {


        StringBuilder commandMaker = gameState.getNickNames().get(session.getId());
        //Make sure the Command Issuer is the same person as the units Owner
        if(commandMaker.toString().compareTo(commandStructure.getOwnerNickName()) == 0){
            System.out.println("CreateCommandStructureCommand: Execute(): conditional passed");

            //Inject Dependents
            commandStructure.setGameState(gameState);
            //get a singleton DataServiceBus inside the unit so that objects can register themselves

            //Set the Unit's string builder to the sessions
            commandStructure.setOwnerNickName(commandMaker);

            //Allow the Unit to handle its own creation
            commandStructure.createSelf(gameState.getPlayerData().get(commandMaker));
        }
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    /** a method that returns the loadstates this command is expected to work with
     * @return - An array with 1 value, (LoadState.PRE_GAME)
     */
    @Override
    public LoadState[] expectedLoadStates() {
        return new LoadState[]{LoadState.PRE_GAME};
    }
}
