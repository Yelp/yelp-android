package com.yelp.clientlib.exception.exceptions;

public class InvalidOAuthCredentials extends YelpAPIError {
    public InvalidOAuthCredentials(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
