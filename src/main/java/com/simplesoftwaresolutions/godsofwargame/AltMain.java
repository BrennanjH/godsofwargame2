package com.simplesoftwaresolutions.godsofwargame;

import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;

/**
 * A class that holds a test main method, not used during live production
 */
public class AltMain {

    public static void main(String[] args) {


        ServerRole role1 = ServerRole.LOBBY_MEMBER;

        ServerRole role2 = role1;

        role1 = ServerRole.LOBBY_HOST;

        System.out.println(role1);
        System.out.println(role2);

    }

}

