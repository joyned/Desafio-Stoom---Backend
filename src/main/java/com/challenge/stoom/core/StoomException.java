package com.challenge.stoom.core;

public class StoomException extends RuntimeException {
    public StoomException(String fiendlyMessage, Exception e) {
        super(fiendlyMessage, e);
        super.printStackTrace();
    }

    public StoomException(String friendlyMessage) {
        super(friendlyMessage);
        super.printStackTrace();
    }
}
