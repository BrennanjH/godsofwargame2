package com.simplesoftwaresolutions.godsofwargame.location;

/**
 * A class that stores the xy coords of a server object
 */
public class PositionalCord implements CoordinatePlane{

    protected int xCord;
    protected int yCord;

    @Override
    public int getX() {
        return xCord;
    }

    @Override
    public int getY() {
        return yCord;
    }
}
