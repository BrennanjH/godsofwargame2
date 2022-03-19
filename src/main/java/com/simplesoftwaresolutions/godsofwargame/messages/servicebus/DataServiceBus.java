package com.simplesoftwaresolutions.godsofwargame.messages.servicebus;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.Changeable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Createable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Destroyable;

import java.util.ArrayList;
import java.util.List;

/**
 * A SINGLETON class that holds data pertaining to state changes in one of three forms, Needs to be thread safe
 */
public class DataServiceBus {

    private static DataServiceBus singletonDSB;

    private DataServiceBus(){
        changeables = new ArrayList<>();
        destroyables = new ArrayList<>();
        createables = new ArrayList<>();
    }

    public static DataServiceBus getInstance(){
        if(singletonDSB == null){
            singletonDSB = new DataServiceBus();
        }
        return singletonDSB;
    }

    private List<Changeable> changeables;
    private List<Destroyable> destroyables;
    private List<Createable> createables;

    public synchronized void addToChangeables(Changeable oldObject){
        changeables.add(oldObject);
    }
    public synchronized void addToDestroyables(Destroyable removedObject){
        destroyables.add(removedObject);
    }
    public synchronized void addToCreateables(Createable newObject){
        createables.add(newObject);
    }

}
