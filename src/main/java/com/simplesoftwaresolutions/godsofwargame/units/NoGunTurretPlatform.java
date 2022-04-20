package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

import java.util.List;

/**
 * A turret platform that can't shoot, used by units that shouldn't be able to actually shoot something
 */
public class NoGunTurretPlatform extends AbstractTurretPlatform{


    public NoGunTurretPlatform() {

        super(0, 0, 0);
    }

    @Override
    public void attack() {
        return;
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public void findTarget(List<PlayerProfile> playerProfiles, AbstractUnitObject owner) {
        return;
    }

    @Override
    public int priceOfSelf() {
        return 0;
    }
}
