package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerValues;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;

/**
 * Stores references to all business object subclasses
 */
public class MappingTableReferences {

    static String standardUnit = new StandardUnit().getClass().getName();
    static String playerValues = new PlayerValues(null,null).getClass().getName();
    static String playerProfile = new PlayerProfile(null,null,null).getClass().getName();

    public void useMapper(BusinessObject bo){
        String objectType = bo.getClass().getName();
        if(objectType.compareTo(standardUnit) == 0){

        } else if (objectType.compareTo(playerValues)==0) {

        } else if (objectType.compareTo(playerProfile)==0){

        }
    }

    public static String getStandardUnit() {
        return standardUnit;
    }

    public static void setStandardUnit(String standardUnit) {
        MappingTableReferences.standardUnit = standardUnit;
    }

    public static String getPlayerValues() {
        return playerValues;
    }

    public static void setPlayerValues(String playerValues) {
        MappingTableReferences.playerValues = playerValues;
    }

    public static String getPlayerProfile() {
        return playerProfile;
    }

    public static void setPlayerProfile(String playerProfile) {
        MappingTableReferences.playerProfile = playerProfile;
    }
}
