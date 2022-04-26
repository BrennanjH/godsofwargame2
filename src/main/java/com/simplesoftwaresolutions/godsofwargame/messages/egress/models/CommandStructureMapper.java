package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.units.CommandStructure;

public class CommandStructureMapper implements Mapper {

    @Override
    public Model map(BusinessObject bo) {
        CommandStructure su = (CommandStructure) bo;
        return new CommandStructureModel(su.getClassName(),
                su.getLocationData(),
                su.getMeta());
    }
}
