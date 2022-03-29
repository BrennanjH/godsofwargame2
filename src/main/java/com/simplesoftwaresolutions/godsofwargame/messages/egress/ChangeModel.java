/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.egress;

import com.simplesoftwaresolutions.godsofwargame.messages.models.MappingTableReferences;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerValues;
import com.simplesoftwaresolutions.godsofwargame.units.StandardUnit;

import java.util.List;

/** An object that holds onto the objects that have changed since the last message sent
 * does not send non-changed objects
 *
 * @author brenn
 */
public class ChangeModel extends AbstractReturnModel {

    private List<Changeable> update;
    private List<Destroyable> remove;
    private List<Creatable> newObjects;
    
    public ChangeModel(DataServiceBus dsb){

        
        //Get list of Changed objects
        this.update = dsb.getChangeables();
        
        //Get list of deleted objects
        this.remove = dsb.getDestroyables();
        
        //Get List of new objects
        newObjects = dsb.getCreatables();


        //Clear lists
        //TODO - clear service bus

        //map each object to it's model
        for (BusinessObject bo :
                update) {

            String objectType = bo.getClass().getName();
            if(objectType.compareTo(MappingTableReferences.getStandardUnit()) == 0){
                StandardUnit standardUnit = (StandardUnit) bo;
            } else if (objectType.compareTo(MappingTableReferences.getPlayerValues())==0) {
                PlayerValues playerValues = (PlayerValues) bo;
            } else if (objectType.compareTo(MappingTableReferences.getPlayerProfile())==0){
                PlayerProfile profile = (PlayerProfile) bo;
            }

        }
        for (BusinessObject bo :
                remove) {

            String objectType = bo.getClass().getName();
            if(objectType.compareTo(MappingTableReferences.getStandardUnit()) == 0){
                StandardUnit standardUnit = (StandardUnit) bo;
            } else if (objectType.compareTo(MappingTableReferences.getPlayerValues())==0) {
                PlayerValues playerValues = (PlayerValues) bo;
            } else if (objectType.compareTo(MappingTableReferences.getPlayerProfile())==0){
                PlayerProfile profile = (PlayerProfile) bo;
            }

        }
        for (BusinessObject bo :
                newObjects) {

            String objectType = bo.getClass().getName();
            if(objectType.compareTo(MappingTableReferences.getStandardUnit()) == 0){
                StandardUnit standardUnit = (StandardUnit) bo;
            } else if (objectType.compareTo(MappingTableReferences.getPlayerValues())==0) {
                PlayerValues playerValues = (PlayerValues) bo;
            } else if (objectType.compareTo(MappingTableReferences.getPlayerProfile())==0){
                PlayerProfile profile = (PlayerProfile) bo;
            }

        }

    }


}
