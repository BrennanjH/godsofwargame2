package com.simplesoftwaresolutions.godsofwargame;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Water;

/**
 * A class that holds a test main method, not used during live production
 */
public class AltMain {

    public static void main(String[] args) {
        Water water = new Water();
        System.out.println(water.getClass().toString());
    }
}
