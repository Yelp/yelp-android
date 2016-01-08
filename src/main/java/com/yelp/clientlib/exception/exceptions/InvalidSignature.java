package com.yelp.clientlib.exception.exceptions;

public class InvalidSignature extends YelpAPIError {
    public InvalidSignature(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
