package com.yelp.clientlib.exception;

import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(APIErrorUtils.class)
public class APIErrorUtilsTest {

    @Test
    public void testParseNullResponseBody() throws IOException {
        int errorCode = 500;
        String errorMessage = "Internal Server Error";

        YelpAPIError error = APIErrorUtils.parseError(errorCode, errorMessage, null);
        Assert.assertTrue(error instanceof UnexpectedAPIError);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertNull(error.getText());
        Assert.assertNull(error.getErrorId());
    }

    @Test
    public void testParseBusinessUnavailable() throws IOException {
        int errorCode = 400;
        String errorMessage = "Bad Request";
        String errorId = "BUSINESS_UNAVAILABLE";
        String errorText = "Business information is unavailable";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        YelpAPIError error = APIErrorUtils.parseError(errorCode, errorMessage, errorJsonBody);
        Assert.assertTrue(error instanceof BusinessUnavailable);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertEquals("BUSINESS_UNAVAILABLE", error.getErrorId());
        Assert.assertEquals("Business information is unavailable", error.getText());
    }

    @Test
    public void testParseInternalError() throws IOException {
        int errorCode = 500;
        String errorMessage = "Internal Server Error";
        String errorId = "INTERNAL_ERROR";
        String errorText = "Some internal error happened";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        YelpAPIError error = APIErrorUtils.parseError(errorCode, errorMessage, errorJsonBody);
        Assert.assertTrue(error instanceof InternalError);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertEquals(errorId, error.getErrorId());
        Assert.assertEquals(errorText, error.getText());
    }

    @Test
    public void testParseUnexpectedAPIError() throws IOException {
        int errorCode = 400;
        String errorMessage = "Bad Request";
        String errorId = "COULD_BE_ANY_THING_NOT_DEFINED";
        String errorText = "Woops, there is something unexpected happened";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        YelpAPIError error = APIErrorUtils.parseError(errorCode, errorMessage, errorJsonBody);
        Assert.assertTrue(error instanceof UnexpectedAPIError);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertEquals(errorId, error.getErrorId());
        Assert.assertEquals(errorText, error.getText());
    }

    @Test(expected = IOException.class)
    public void testParseInvalidJsonBody() throws IOException {
        int errorCode = 500;
        String errorMessage = "Internal Server Error";
        String errorHTMLBody = "<html><title>This is not JSON</title></html>";

        APIErrorUtils.parseError(errorCode, errorMessage, errorHTMLBody);
    }

    private String generateErrorJsonString(String errorId, String text) {
        String errorJsonStringFormat = "{\"error\": {\"id\": \"%s\", \"text\": \"%s\"}}";
        return String.format(errorJsonStringFormat, errorId, text);
    }
}
