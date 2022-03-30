package com.simplesoftwaresolutions.godsofwargame.messages.servicebus;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.Changeable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Creatable;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.Destroyable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A SINGLETON class that holds data pertaining to state changes in one of three forms, Needs to be thread safe
 */
public class DataServiceBus {

    private static DataServiceBus singletonDSB;

    private DataServiceBus(){
        changeables = new ArrayList<>();
        destroyables = new ArrayList<>();
        creatables = new ArrayList<>();
    }

    public synchronized static DataServiceBus getInstance(){
        if(singletonDSB == null){
            singletonDSB = new DataServiceBus();
        }
        return singletonDSB;
    }

    private volatile List<Changeable> changeables;
    private volatile List<Destroyable> destroyables;
    private volatile List<Creatable> creatables;

    public synchronized void addToChangeables(Changeable changedObject){
        if(!changeables.contains(changedObject))
            changeables.add(changedObject);
    }
    public synchronized void addToDestroyables(Destroyable removedObject){
        if(!destroyables.contains(removedObject))
            destroyables.add(removedObject);
    }
    public synchronized void addToCreatables(Creatable newObject){
        if(!creatables.contains(newObject))
            creatables.add(newObject);
    }

    public List<Changeable> getChangeables() {
        return Collections.unmodifiableList(changeables);
    }

    public List<Destroyable> getDestroyables() {
        return Collections.unmodifiableList(destroyables);
    }

    public List<Creatable> getCreatables() {
        return Collections.unmodifiableList(creatables);
    }

    public synchronized void clearChangeables(){
        changeables.clear();
    }
    public synchronized void clearDestroyables(){
        destroyables.clear();
    }
    public synchronized void clearCreatables(){
        creatables.clear();
    }

}
