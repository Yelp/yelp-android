package com.yelp.clientlib.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.Response;
import com.yelp.clientlib.exception.exceptions.AreaTooLarge;
import com.yelp.clientlib.exception.exceptions.BadCategory;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.ExceededReqs;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.InvalidOAuthCredentials;
import com.yelp.clientlib.exception.exceptions.InvalidOAuthUser;
import com.yelp.clientlib.exception.exceptions.InvalidParameter;
import com.yelp.clientlib.exception.exceptions.InvalidSignature;
import com.yelp.clientlib.exception.exceptions.MissingParameter;
import com.yelp.clientlib.exception.exceptions.MultipleLocations;
import com.yelp.clientlib.exception.exceptions.SSLRequired;
import com.yelp.clientlib.exception.exceptions.UnavailableForLocation;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.UnspecifiedLocation;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;

import java.io.IOException;

/**
 * {@link Interceptor} to parse and transform the HTTP errors.
 */
public class ErrorHandlingInterceptor implements Interceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Intercept HTTP responses and raise a {@link YelpAPIError} if the response code is not 2xx.
     *
     * @param chain {@link com.squareup.okhttp.Interceptor.Chain} object for sending the HTTP request.
     * @return response
     * @throws IOException {@link YelpAPIError} generated depends on the response error id.
     */
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

        JsonNode errorJsonNode = objectMapper.readTree(responseBody).path("error");
        String errorId = errorJsonNode.path("id").asText();
        String errorText = errorJsonNode.path("text").asText();

        if (errorJsonNode.has("field")) {
            errorText += ": " + errorJsonNode.path("field").asText();
        }

        switch (errorId) {
            case "AREA_TOO_LARGE":
                return new AreaTooLarge(code, message, errorId, errorText);
            case "BAD_CATEGORY":
                return new BadCategory(code, message, errorId, errorText);
            case "BUSINESS_UNAVAILABLE":
                return new BusinessUnavailable(code, message, errorId, errorText);
            case "EXCEEDED_REQS":
                return new ExceededReqs(code, message, errorId, errorText);
            case "INTERNAL_ERROR":
                return new InternalError(code, message, errorId, errorText);
            case "INVALID_OAUTH_CREDENTIALS":
                return new InvalidOAuthCredentials(code, message, errorId, errorText);
            case "INVALID_OAUTH_USER":
                return new InvalidOAuthUser(code, message, errorId, errorText);
            case "INVALID_PARAMETER":
                return new InvalidParameter(code, message, errorId, errorText);
            case "INVALID_SIGNATURE":
                return new InvalidSignature(code, message, errorId, errorText);
            case "MISSING_PARAMETER":
                return new MissingParameter(code, message, errorId, errorText);
            case "MULTIPLE_LOCATIONS":
                return new MultipleLocations(code, message, errorId, errorText);
            case "SSL_REQUIRED":
                return new SSLRequired(code, message, errorId, errorText);
            case "UNAVAILABLE_FOR_LOCATION":
                return new UnavailableForLocation(code, message, errorId, errorText);
            case "UNSPECIFIED_LOCATION":
                return new UnspecifiedLocation(code, message, errorId, errorText);
            default:
                return new UnexpectedAPIError(code, message, errorId, errorText);
        }
    }
}
