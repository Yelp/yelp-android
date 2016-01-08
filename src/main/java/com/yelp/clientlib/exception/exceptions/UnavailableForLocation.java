package com.yelp.clientlib.exception.exceptions;

public class UnavailableForLocation extends YelpAPIError {
    public UnavailableForLocation(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
