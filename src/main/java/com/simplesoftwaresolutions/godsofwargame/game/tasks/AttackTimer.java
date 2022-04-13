package com.simplesoftwaresolutions.godsofwargame.game.tasks;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * A TimerTask that when run will cause all existing units to attack their targets
 * if the target is not set or is invalid it will attempt to find a target and if it still fails it simply won't shoot
 * anything
 */
public class AttackTimer extends TimerTask {

    private GameState gameState;

    public AttackTimer(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void run() {
        //get a list of each Player in the game
        List<PlayerProfile> playerData = new ArrayList<>();

        for(StringBuilder playerString : gameState.getPlayerData().keySet()) {

            playerData.add(gameState.getPlayerData().get(playerString));

        }

        //Loop through each unit and handle it's attack
        List<AbstractUnitObject> playersUnits;

        for (PlayerProfile p :
                playerData) {
            playersUnits = p.getPlayerValues().getUnits();

            //if the target isn't permitted switch unit to shooting at another hostile players units
            for (AbstractUnitObject unit :
                    playersUnits) {

                AbstractUnitObject targetedUnit = unit.getTurretPlatform().getTarget().getTargetedUnit();

                //assign a valid target if needed
                if (! unit.getTurretPlatform().canShoot(unit) ){
                    unit.getTurretPlatform().findTarget(playerData, unit);
                }
            }

        }




    }

    /** Finds a new AbstractUnitObject that can become a unit's target
     * @param unit The unit that is to receive a new target
     */
    private void assignNewTarget(AbstractUnitObject unit){

    }

}
