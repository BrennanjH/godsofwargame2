package com.simplesoftwaresolutions.godsofwargame.units;

import lombok.Data;

/**
 * A class that represents an AbstractUnitObject that is being stored for some purpose related to the instantiating object
 */
@Data
public class Target {
    private AbstractUnitObject targetedUnit;

}
