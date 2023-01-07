package com.simplesoftwaresolutions.godsofwargame.game.tasks;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GameTimeProcessTasks extends TimerTask {
    private GameState gameState;

    public GameTimeProcessTasks(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
        //Allow all units to attempt to attack
        handleAttack();

        //Remove dead units
        cleanBattlefield();

        //Move all existing units according to their routes
        handleMovement();
    }

    /**
     * Removes the dead units from the field, this is decided on a units health being <= 0
     */
    private void cleanBattlefield() {
        List<PlayerProfile> playerData = getPlayerProfiles();

        //Loop through each unit and handle its death
        List<AbstractUnitObject> playersUnits;

        for (PlayerProfile p :
                playerData) {
            playersUnits = p.getPlayerValues().getUnits();

            //loop through players units to decide if each one is dead
            for (AbstractUnitObject unit :
                    playersUnits) {

                //WARNING - perhaps the unit should have its own check for its own death conditions?
                if(unit.getHealthControlled() <= 0) {
                    //unit has died
                    unit.removeSelf();
                }

            }

        }
    }

    @NotNull
    private List<PlayerProfile> getPlayerProfiles() {
        //get a list of each Player in the game
        List<PlayerProfile> playerData = new ArrayList<>();

        for(StringBuilder playerString : gameState.getPlayerData().keySet()) {

            playerData.add(gameState.getPlayerData().get(playerString));

        }
        return playerData;
    }

    private void handleMovement() {
        //get a list of each Player in the game
        List<PlayerProfile> playerData = getPlayerProfiles();

        //for each unit in the game handle it's movement
        List<AbstractUnitObject> playersUnits;

        for (PlayerProfile p :
                playerData) {
            playersUnits = p.getPlayerValues().getUnits();

            //for each loop of each unit owned by PlayerProfile p
            for (AbstractUnitObject unit :
                    playersUnits) {
                //Unlike attacking, movement is micromanaged by the player only, as such no effort to decide a path is made
                //by the server it lets each unit decide when it can move
                if (unit.getPath().isEmpty() ){
                    continue;
                }
                unit.move();
            }

        }
    }

    private void handleAttack() {
        //get a list of each Player in the game
        List<PlayerProfile> playerData = getPlayerProfiles();

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

                //Damage the targeted unit
                unit.attack();
            }

        }
    }


}
