package com.yelp.clientlib.exception.exceptions;

public class InvalidParameter extends YelpAPIError {
    public InvalidParameter(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
