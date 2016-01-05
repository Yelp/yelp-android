package com.yelp.clientlib.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;
import com.yelp.clientlib.exception.exceptions.YelpError;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Enum to list all YelpAPIErrors possibly contained in the error response.<p/>
 *
 * {@link #parseError(int, String, String)} should be used to construct a YelpAPIError from a JSON formatted response
 * body string. Any undefined error will cause an {@link UnexpectedAPIError} be raised.
 */
public enum YelpAPIErrors {
    BUSINESS_UNAVAILABLE,
    INTERNAL_ERROR;
    // TODO: Add more Exceptions.

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Set<String> errorNameSet = new HashSet<>();

    static {
        // Initiate errorNameSet so we can easily check existence of errorClasses by using errorIds.
        for (YelpAPIErrors error : EnumSet.allOf(YelpAPIErrors.class)) {
            errorNameSet.add(error.name());
        }
    }

    public static YelpAPIError parseError(int code, String message, String responseBody) throws YelpError {
        if (responseBody == null) {
            return new UnexpectedAPIError(code, message);
        }

        try {
            JsonNode errorJsonNode = objectMapper.readTree(responseBody);
            String errorId = errorJsonNode.path("error").path("id").asText();
            String errorText = errorJsonNode.path("error").path("text").asText();

            return newError(code, message, errorId, errorText);
        } catch (IOException e) {
            // Fail to parse responseBody.
            throw new YelpError(e);
        }
    }

    private static YelpAPIError newError(int code, String message, String errorId, String text) {
        if (!errorNameSet.contains(errorId)) {
            return new UnexpectedAPIError(code, message, errorId, text);
        }

        switch (YelpAPIErrors.valueOf(errorId)) {
            case BUSINESS_UNAVAILABLE:
                return new BusinessUnavailable(code, message, errorId, text);
            case INTERNAL_ERROR:
                return new InternalError(code, message, errorId, text);
            default:
                // will NEVER be executed since we check the existence before the switch statement.
                return new UnexpectedAPIError(code, message, errorId, text);
        }
    }
}

