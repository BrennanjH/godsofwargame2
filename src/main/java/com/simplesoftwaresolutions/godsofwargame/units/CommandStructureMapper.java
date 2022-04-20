package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Model;

public class CommandStructureMapper implements Mapper {

    @Override
    public Model map(BusinessObject bo) {
        CommandStructure su = (CommandStructure) bo;
        return new CommandStructureModel(su.getClassName(),
                su.getLocationData(),
                su.getMeta());
    }
}
