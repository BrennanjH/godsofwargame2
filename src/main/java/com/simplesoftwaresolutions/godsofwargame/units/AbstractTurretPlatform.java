/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.units;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
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
    
    
    
    abstract public void attack();
    abstract public boolean isBuilt();

    
    
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

    public abstract int priceOfSelf();
}
