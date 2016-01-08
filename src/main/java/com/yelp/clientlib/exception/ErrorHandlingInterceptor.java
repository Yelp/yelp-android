package com.yelp.clientlib.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;

import java.io.IOException;

/**
 * {@link Interceptor} to parse and transform the HTTP errors.
 */
public class ErrorHandlingInterceptor implements Interceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            throw parseError(
                    response.code(),
                    response.message(),
                    response.body() != null ? response.body().string() : null
            );
        }
        return response;
    }

    private YelpAPIError parseError(int code, String message, String responseBody) throws IOException {
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

    private YelpAPIError newError(int code, String message, String errorId, String text) {
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
