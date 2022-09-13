package com.simplesoftwaresolutions.godsofwargame;

import com.simplesoftwaresolutions.godsofwargame.dto.ServerMetrics;

/**
 * A class that holds a test main method, not used during live production
 */
public class AltMain {

    public static void main(String[] args) {
        ServerMetrics metrics = new ServerMetrics();
        ServerMetrics metrics2 = metrics;
        metrics.setPlayerCount(203);
        System.out.println(metrics2.getPlayerCount());
    }

}

