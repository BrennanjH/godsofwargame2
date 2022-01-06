package com.simplesoftwaresolutions.godsofwargame.messages;

/**
 * An execption that can be thrown if a message created object is missing fields that should never be missing
 * as the frontend should automatically fill them out with some type of value
 */
public class NullExpectedField extends Throwable {

}
