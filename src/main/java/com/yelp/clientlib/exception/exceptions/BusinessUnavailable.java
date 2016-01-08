package com.yelp.clientlib.exception.exceptions;

public class BusinessUnavailable extends YelpAPIError {
    public BusinessUnavailable(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
