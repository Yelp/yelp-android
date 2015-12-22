package com.yelp.clientlib.exception.exceptions;

import java.io.IOException;

public class YelpError extends IOException {

    public YelpError() {
        super();
    }

    public YelpError(String message) {
        super(message);
    }

    public YelpError(String message, Throwable cause) {
        super(message, cause);
    }

    public YelpError(Throwable cause) {
        super(cause);
    }
}
