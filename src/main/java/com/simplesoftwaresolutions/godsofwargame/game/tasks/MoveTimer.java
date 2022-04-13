package com.simplesoftwaresolutions.godsofwargame.game.tasks;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/* MoveTimer manages the movement of objects by identifying the next path on each units route and then changing the units
* location to the new postion, as it is a TimerTask the frequency in which this occurs is when
*
 */
public class MoveTimer extends TimerTask {
    private GameState gameState;

    public MoveTimer(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
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
}
