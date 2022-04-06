/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

import java.util.List;

/** A class that represents the anti-air guns attack behavior
 *
 * @author brenn
 */
public class AntiAirGunTurretPlatform extends AbstractTurretPlatform {
    
    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public void findTarget(List<PlayerProfile> playerProfiles, AbstractUnitObject owner) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int priceOfSelf() {
        return 100;
    }

}
