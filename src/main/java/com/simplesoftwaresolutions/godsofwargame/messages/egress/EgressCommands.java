package com.simplesoftwaresolutions.godsofwargame.messages.egress;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;


public interface EgressCommands {

    /** A method that sends an already built command to the correct players
     * @param gameState The gameState used to build the message
     */
    public void sendToPlayers(GameState gameState);
}
