package com.yelp.clientlib.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;

import java.io.IOException;

/**
 * <p>Util class to parse out API error responses and generate corresponding exceptions.<p/>
 *
 * {@link #parseError(int, String, String)} should be used to construct a YelpAPIError from a JSON formatted response
 * body string. Any undefined error will cause an {@link UnexpectedAPIError} be raised.
 */
public class APIErrorUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static YelpAPIError parseError(int code, String message, String responseBody) throws IOException {
        if (responseBody == null) {
            return new UnexpectedAPIError(code, message);
        }

        JsonNode errorJsonNode = objectMapper.readTree(responseBody);
        return newError(
                code,
                message,
                errorJsonNode.path("error").path("id").asText(),
                errorJsonNode.path("error").path("text").asText()
        );
    }

    private static YelpAPIError newError(int code, String message, String errorId, String text) {
        // TODO: Add more Exceptions.

        switch (errorId) {
            case "BUSINESS_UNAVAILABLE":
                return new BusinessUnavailable(code, message, errorId, text);
            case "INTERNAL_ERROR":
                return new InternalError(code, message, errorId, text);
            default:
                return new UnexpectedAPIError(code, message, errorId, text);
        }
    }
}

