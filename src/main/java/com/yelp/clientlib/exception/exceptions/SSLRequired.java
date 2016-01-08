package com.yelp.clientlib.exception.exceptions;

public class SSLRequired extends YelpAPIError {
    public SSLRequired(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
