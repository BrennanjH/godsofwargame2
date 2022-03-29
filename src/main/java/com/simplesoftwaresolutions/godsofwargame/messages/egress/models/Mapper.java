package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;

public interface Mapper {
    public Model map(BusinessObject bo);
}
