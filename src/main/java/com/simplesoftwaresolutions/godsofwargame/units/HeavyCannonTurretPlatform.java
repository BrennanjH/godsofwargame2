/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

import java.util.List;

/**
 *
 * @author brenn
 */
public class HeavyCannonTurretPlatform extends AbstractTurretPlatform{

    @JsonCreator
    public HeavyCannonTurretPlatform(@JsonProperty("damage") int damage,
                                     @JsonProperty("fireSpeed")int fireSpeed,
                                     @JsonProperty("range")int targetingRange) {
        super(damage,fireSpeed,targetingRange);
    }

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
        for (PlayerProfile profile :
                playerProfiles) {
            if(profile.getNickname().compareTo(owner.getOwnerNickName()) == 0)
                continue;
            for (AbstractUnitObject unit :
                    profile.getPlayerValues().getUnits()) {

                if(canShoot(owner, unit) ) {
                    System.out.println(owner.getOwnerNickName());
                    System.out.println(unit.getOwnerNickName());
                    owner.setTarget(unit);
                    return;
                }
            }
        }
    }


    @Override
    public int priceOfSelf() {

        return 300;
    }

}
