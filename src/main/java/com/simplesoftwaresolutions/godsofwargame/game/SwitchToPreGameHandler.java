package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;

/**
 * A class that assists the server in switching
 */
public class SwitchToPreGameHandler {

    /** Preps the gameState object to be a PRE_GAME load level making sure that player roles are fixed, loadstate is changed
     * and that a message notifying changes in loadstate occurs
     * @param gameState The servers gameState object
     */
    public void SwitchToPreGame(GameState gameState) {
        //Update loadstate
        gameState.loadState = LoadState.PRE_GAME;
        //Update all player roles
        for(StringBuilder s : gameState.getPlayerData().keySet()) {
            gameState.getPlayerData().get(s).setServerRole(ServerRole.GAME_MEMBER);
        }
        //Create and send a message to all players saying that a gamestate change has occured
        //TODO
    }

}
