package com.simplesoftwaresolutions.godsofwargame.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that stores the xy coords of a server object
 */
public class PositionalCord {

    protected int xCord;
    protected int yCord;

    @JsonCreator
    public PositionalCord(@JsonProperty("x") int xCord,
                          @JsonProperty("y") int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public int getX() {
        return xCord;
    }


    public int getY() {
        return yCord;
    }


    public void setX(int x) {
        xCord = x;
    }


    public void setY(int y) {
        yCord = y;
    }


}
