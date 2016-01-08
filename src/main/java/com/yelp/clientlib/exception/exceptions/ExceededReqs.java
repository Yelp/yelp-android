package com.yelp.clientlib.exception.exceptions;

public class ExceededReqs extends YelpAPIError {
    public ExceededReqs(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
