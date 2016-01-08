package com.yelp.clientlib.exception.exceptions;

public class MultipleLocations extends YelpAPIError {
    public MultipleLocations(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
