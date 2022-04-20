package com.simplesoftwaresolutions.godsofwargame.messages.servicebus;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Mapper;

/**
 * an envelope of an object and its preferred mapper which will allow an object to be sent in the most efficient manner
 */
public class Envelope {

    public Envelope(Mapper objectMapper, BusinessObject payload) {
        this.objectMapper = objectMapper;
        this.payload = payload;
    }

    private Mapper objectMapper;

    private BusinessObject payload;

    public Mapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(Mapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BusinessObject getPayload() {
        return payload;
    }

    public void setPayload(BusinessObject payload) {
        this.payload = payload;
    }
}
