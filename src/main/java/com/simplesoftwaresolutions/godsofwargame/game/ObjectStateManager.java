package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.AbstractReturnModel;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.ChangeModel;
import org.springframework.beans.factory.annotation.Autowired;

/*
A class that depends on a gameState object and can read the current state to decide whether a new message needs
to be built
 */
public class ObjectStateManager {

    private final GameState gameState;

    @Autowired
    public ObjectStateManager(GameState gameState){
        this.gameState = gameState;
    }

    /** A send ready model object is returned, the nature of this object is determined by this class
     * @return A send ready model object is returned
     */
    public AbstractReturnModel returnModel() {

        return new ChangeModel(gameState);
    }
}
