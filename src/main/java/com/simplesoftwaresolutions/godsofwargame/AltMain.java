package com.simplesoftwaresolutions.godsofwargame;

/**
 * A class that holds a test main method, not used during live production
 */
public class AltMain {

    public static void main(String[] args) {
        testString ts = new testString();
        System.out.println(ts.getCheckString());
        String holder = ts.getCheckString();
        holder = "The value has changed";
        System.out.println(ts.getCheckString());
    }

}

