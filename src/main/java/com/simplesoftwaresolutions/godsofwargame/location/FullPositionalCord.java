package com.simplesoftwaresolutions.godsofwargame.location;

/**
 * A 3d positional system that allows players to detail stacks of objects
 */
public class FullPositionalCord extends PositionalCord implements ExtendedCoordinatePlane{

    protected int zCord;

    @Override
    public int getZ() {
        return zCord;
    }
}
