package com.simplesoftwaresolutions.godsofwargame.game.tasks;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;

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
        handleAttack();
        handleMovement();
    }

    private void handleMovement() {
        //get a list of each Player in the game
        List<PlayerProfile> playerData = new ArrayList<>();

        for(StringBuilder playerString : gameState.getPlayerData().keySet()) {

            playerData.add(gameState.getPlayerData().get(playerString));

        }

        //for each unit in the game handle it's movement
        List<AbstractUnitObject> playersUnits;

        for (PlayerProfile p :
                playerData) {
            playersUnits = p.getPlayerValues().getUnits();

            //for each loop of each unit owned by PlayerProfile p
            for (AbstractUnitObject unit :
                    playersUnits) {
                //Unlike attacking units are micromanaged by the player only as such no effort to decide a path is made
                //by the server it lets each unit decide when it can move
                unit.move();
            }

        }
    }

    private void handleAttack() {
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


}
