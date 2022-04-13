package com.simplesoftwaresolutions.godsofwargame.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A 3d positional system that allows players to detail stacks of objects
 */
public class FullPositionalCord extends PositionalCord {

    protected int zCord;

    public FullPositionalCord(){
        super(0,0);
        zCord = 0;
    }
    @JsonCreator
    public FullPositionalCord(@JsonProperty("x") int x,
                              @JsonProperty("y") int y,
                              @JsonProperty("z") int zCord) {
        super(x,y);
        this.zCord = zCord;
    }

    public int getZ() {
        return zCord;
    }

    public void setzCord(int zCord) {
        this.zCord = zCord;
    }
}
