package com.yelp.clientlib.exception.exceptions;

public class BadCategory extends YelpAPIError {
    public BadCategory(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
