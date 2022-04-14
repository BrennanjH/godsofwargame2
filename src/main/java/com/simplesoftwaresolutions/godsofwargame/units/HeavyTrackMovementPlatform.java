/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

/** A slow moving but higher health ground platform
 *
 * @author brenn
 */

public class HeavyTrackMovementPlatform extends AbstractMovementPlatform{

    @Override
    protected void move(PositionalCord locationData, BoardManager boardManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public int priceOfSelf() {
        return 300;
    }


}
