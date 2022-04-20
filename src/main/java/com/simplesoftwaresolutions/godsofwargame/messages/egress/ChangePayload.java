/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.egress;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Model;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.DataServiceBus;
import com.simplesoftwaresolutions.godsofwargame.messages.servicebus.Envelope;

import java.util.ArrayList;
import java.util.List;

/** An object that holds onto the objects that have changed since the last message sent
 * does not send non-changed objects
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class ChangePayload extends AbstractReturnModel {

    private List<Model> update;
    private List<Model> remove;
    private List<Model> newObjects;
    
    public ChangePayload(DataServiceBus dsb){
        //Protect the service bus from change during this operation
        synchronized (dsb) {
            //map each object to it's model and store it in appropriate model list
            this.update = cycleList(dsb.getChangeables());
            this.remove = cycleList(dsb.getDestroyables());
            newObjects = cycleList(dsb.getCreatables());

            //Clear lists
            dsb.clearChangeables();
            dsb.clearCreatables();
            dsb.clearDestroyables();
        }
    }


    private static <T extends Envelope> List<Model> cycleList(List<T> dataList){
        Model temp;
        List<Model> modelList = new ArrayList<>();
        for (Envelope envelope :
                dataList) {

            temp = envelope.getObjectMapper().map(envelope.getPayload());

            modelList.add(temp);

        }
        return modelList;
    }

}
