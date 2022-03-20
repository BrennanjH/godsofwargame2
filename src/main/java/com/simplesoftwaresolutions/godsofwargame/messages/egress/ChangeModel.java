/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.egress;

import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;

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

        //TODO - clear service bus
        //Clear lists

    }



}
