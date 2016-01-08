package com.yelp.clientlib.exception.exceptions;

public class InternalError extends YelpAPIError {
    public InternalError(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
