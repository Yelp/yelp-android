package com.yelp.clientlib.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;
import com.yelp.clientlib.exception.exceptions.YelpError;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum to list out every YelpAPIErrors could be raised by parsing error responses.
 *
 * {@link #parseError(int, String, String)} should be used to construct an YelpAPIError from a JSON formatted
 * response body string. Any un-defined error will cause an {@link UnexpectedAPIError} be raised.
 */
public enum YelpAPIErrors {

    BUSINESS_UNAVAILABLE(BusinessUnavailable.class),
    INTERNAL_ERROR(InternalError.class),
    UNEXPECTED_ERROR(UnexpectedAPIError.class);
    // TODO: Add more Exceptions.

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Set<String> errorNameSet = new HashSet<>();
    private final Class<? extends YelpAPIError> errorClass;

    static {
        // Initiate errorNameSet so we can easily check existence of errorClasses by using errorIds.
        for (YelpAPIErrors error : EnumSet.allOf(YelpAPIErrors.class)) {
            errorNameSet.add(error.name());
        }
    }

    YelpAPIErrors(Class<? extends YelpAPIError> errorClass) {
        this.errorClass = errorClass;
    }

    public static YelpAPIError parseError(int code, String message, String responseBody) throws YelpError {
        if (responseBody == null) {
            return new UnexpectedAPIError(code, message);
        }

        try {
            JsonNode errorJsonNode = objectMapper.readTree(responseBody);
            String errorId = errorJsonNode.path("error").path("id").asText();
            String errorText = errorJsonNode.path("error").path("text").asText();

            if (!errorNameSet.contains(errorId)) {
                return new UnexpectedAPIError(code, message, errorId, errorText);
            }
            return newError(code, message, errorId, errorText);

        } catch (ReflectiveOperationException | IOException e) {
            throw new YelpError(e);
        }
    }

    private Class<? extends YelpAPIError> getErrorClass() {
        return this.errorClass;
    }

    private static YelpAPIError newError(int code, String message, String errorId, String text)
            throws ReflectiveOperationException {

        Class clazz = YelpAPIErrors.valueOf(errorId).getErrorClass();
        Constructor constructor = clazz.getDeclaredConstructor(int.class, String.class, String.class, String.class);
        return (YelpAPIError) constructor.newInstance(code, message, errorId, text);
    }
}

