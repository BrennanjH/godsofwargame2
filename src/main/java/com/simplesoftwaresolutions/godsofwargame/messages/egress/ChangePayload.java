/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages.egress;

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
        String objectType;
        Model temp;
        List<Model> modelList = new ArrayList<>();
        for (Envelope envelope :
                dataList) {
//            temp = null;

            temp = envelope.getObjectMapper().map(envelope.getPayload());
//
//
//            objectType = bo.getClass().getName();
//            if(objectType.compareTo(MappingTableReferences.getStandardUnit()) == 0){
//                StandardUnit standardUnit = (StandardUnit) bo;
//                temp = StandardUnitMapper.mapStandardUnitToStandardUnitModel(standardUnit);
//            } else if (objectType.compareTo(MappingTableReferences.getPlayerValues())==0) {
//                PlayerValues playerValues = (PlayerValues) bo;
//                temp = PlayerValuesMapper.mapPlayerValuesToPlayerValuesModel(playerValues);
//            } else if (objectType.compareTo(MappingTableReferences.getPlayerProfile())==0){
//                PlayerProfile profile = (PlayerProfile) bo;
//                temp = PlayerProfileMapper.mapPlayerProfileToPlayerProfileModel(profile);
//            }
//
//            if(null != temp){
//                modelList.add(temp);
//            }

        }
        return modelList;
    }

}
