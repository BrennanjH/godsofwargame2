package com.simplesoftwaresolutions.godsofwargame;

import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;

/**
 * A class that holds a test main method, not used during live production
 */
public class AltMain {

    public static void main(String[] args) {
        DataServiceBus dsb = DataServiceBus.getInstance();

        dsb.addToCreatables(new StandardUnit());

    }

}

