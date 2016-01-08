package com.yelp.clientlib.util;

import com.yelp.clientlib.exception.exceptions.YelpAPIError;

import org.junit.Assert;

public class ErrorTestUtil {

    /**
     * Verify a {@link YelpAPIError} contains correct information.
     *
     * @param error         The YelpAPIError to be verified.
     * @param expectCode    Expected error code.
     * @param expectMessage Expected error message.
     * @param expectId      Expected error Id.
     * @param expectText    Expected error text.
     */
    public static void verifyErrorContent(
            YelpAPIError error,
            int expectCode,
            String expectMessage,
            String expectId,
            String expectText
    ) {
        Assert.assertEquals(expectCode, error.getCode());
        Assert.assertEquals(expectMessage, error.getMessage());
        Assert.assertEquals(expectId, error.getErrorId());
        Assert.assertEquals(expectText, error.getText());
    }
}
