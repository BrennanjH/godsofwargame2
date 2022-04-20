package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

/**
 * A class that defines a movement platform that can't actually move, This should be used by structures like turrets or buildings
 * if visibility is added this turret should be removed
 */
public class StandStillMovementPlatform extends AbstractMovementPlatform{

    @Override
    protected boolean validateMove(PositionalCord locationData, PositionalCord moveTo, BoardManager boardManager) {
        return false;
    }

    @Override
    public boolean isBuilt() {
        return false;
    }

    @Override
    public int priceOfSelf() {
        return 0;
    }
}
