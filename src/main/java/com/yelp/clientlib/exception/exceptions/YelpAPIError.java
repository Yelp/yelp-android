package com.yelp.clientlib.exception.exceptions;

import java.io.IOException;

public abstract class YelpAPIError extends IOException {
    private int code;
    private String message;
    private String text;
    private String errorId;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getText() {
        return text;
    }

    public String getErrorId() {
        return errorId;
    }

    public YelpAPIError(int code, String message, String errorId, String text) {
        this.code = code;
        this.message = message;
        this.errorId = errorId;
        this.text = text;
    }
}
