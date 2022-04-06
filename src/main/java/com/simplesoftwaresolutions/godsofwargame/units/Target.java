package com.simplesoftwaresolutions.godsofwargame.units;

/**
 * A class that represents an AbstractUnitObject that is being stored for some purpose related to the instantiating object
 */
public class Target {
    private AbstractUnitObject targetedUnit;

    public AbstractUnitObject getTargetedUnit() {
        return targetedUnit;
    }

    public void setTargetedUnit(AbstractUnitObject targetedUnit) {
        this.targetedUnit = targetedUnit;
    }
}
