/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

import java.util.List;

/** A class that helps turretPlatforms follow defined behaviors
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class AbstractTurretPlatform {
    
    /*Variables that define unit behaviors
    damage - How much Damage a unit can do per shot of weapon
    fireSpeed - How often the main weapon can fire
    targetingRange - How close a hostile unit must be before unit can fire
    */
    protected float damage, fireSpeed, targetingRange;

    public AbstractTurretPlatform(float damage, float fireSpeed, float targetingRange) {
        this.damage = damage;
        this.fireSpeed = fireSpeed;
        this.targetingRange = targetingRange;
    }

    protected Target target = new Target();
    
    abstract public void attack();
    abstract public boolean isBuilt();


    public Target getTarget() {
        return target;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(float fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    public float getTargetingRange() {
        return targetingRange;
    }

    public void setTargetingRange(float targetingRange) {
        this.targetingRange = targetingRange;
    }

    /** Checks to see if a stored target is a valid target
     * accomplishes this by checking it's range and affiliation (currently just looks at owner)
     *
     * @param owner - The owner of the firing turret object
     * @return returns true if this is able to fire on it's stored target
     */
    public boolean canShoot( AbstractUnitObject owner ){
        //validate range & affiliation (who owns it)
        //targeting range == x + y
        int targetDistance = Math.abs(target.getTargetedUnit().getLocationData().getX() - owner.getLocationData().getX())
                + Math.abs(target.getTargetedUnit().getLocationData().getY() - owner.getLocationData().getY());

        //If true the target is valid
        if(targetDistance <= targetingRange
        && target.getTargetedUnit().getOwnerNickName().compareTo(owner.getOwnerNickName()) != 0){
            return true;
        } else {
            return false;
        }
    }

    /** A method that checks if a unit can be fired at without requiring the turret object to store the target inside target
     * this protects the unit from any potential changes in its target which might cause the unit to be queued
     *
     * @param owner - The owner of the firing turret object
     * @param target - the prospective target
     * @return - true if the prospective target can be fired at false if not
     */
    public boolean canShoot( AbstractUnitObject owner, AbstractUnitObject target ){
        //validate range & affiliation (who owns it)
        //targeting range == x + y
        int targetDistance = Math.abs(target.getLocationData().getX() - owner.getLocationData().getX())
                + Math.abs(target.getLocationData().getY() - owner.getLocationData().getY());

        //If true the target is valid
        if(targetDistance <= targetingRange
                && target.getOwnerNickName().compareTo(owner.getOwnerNickName()) != 0){
            return true;
        } else {
            return false;
        }
    }

    /** A method that will assign this AbstractTurretInstance a new target based on its own implementation
     * @param playerProfiles - A list of players who the target can sort through to choose a target, The profiles need to
     *                       be verified so that the unit doesn't just pick its own team to shoot at
     */
    public abstract void findTarget(List<PlayerProfile> playerProfiles, AbstractUnitObject owner);

    public abstract int priceOfSelf();
}
