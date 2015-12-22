package com.yelp.clientlib.exception;

import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;
import com.yelp.clientlib.exception.exceptions.YelpError;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(YelpAPIErrors.class)
public class YelpAPIErrorsTest {

    @Test
    public void testParseNullResponseBody() throws YelpError {
        int errorCode = 500;
        String errorMessage = "INTERNAL_ERROR";

        YelpAPIError error = YelpAPIErrors.parseError(errorCode, errorMessage, null);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertNull(error.getText());
        Assert.assertNull(error.getErrorId());
    }

    @Test
    public void testParseBusinessUnavailable() throws YelpError {
        int errorCode = 400;
        String errorMessage = "BAD_REQUEST";
        String errorId = "BUSINESS_UNAVAILABLE";
        String errorText = "Business information is unavailable";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        YelpAPIError error = YelpAPIErrors.parseError(errorCode, errorMessage, errorJsonBody);
        Assert.assertTrue(error instanceof BusinessUnavailable);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertEquals("BUSINESS_UNAVAILABLE", error.getErrorId());
        Assert.assertEquals("Business information is unavailable", error.getText());
    }

    @Test
    public void testParseUnexpectedAPIError() throws YelpError {
        int errorCode = 400;
        String errorMessage = "BAD_REQUEST";
        String errorId = "COULD_BE_ANY_THING_NOT_DEFINED";
        String errorText = "Woops, there is something unexpected happened";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        YelpAPIError error = YelpAPIErrors.parseError(errorCode, errorMessage, errorJsonBody);
        Assert.assertTrue(error instanceof UnexpectedAPIError);
        Assert.assertEquals(errorCode, error.getCode());
        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertEquals(errorId, error.getErrorId());
        Assert.assertEquals(errorText, error.getText());
    }

    @Test(expected = YelpError.class)
    public void testParseInvalidJsonBody() throws YelpError {
        int errorCode = 500;
        String errorMessage = "INTERNAL_ERROR";
        String errorHTMLBody = "<html><title>This is not JSON</title></html>";

        YelpAPIErrors.parseError(errorCode, errorMessage, errorHTMLBody);
    }

    /**
     * This case should never happen, but here we make sure it's working even if ReflectiveOperationException
     * really happens by accident.
     */
    @Test(expected = YelpError.class)
    public void testParseErrorReflectiveOperationExceptionRaiseYelpError() throws Exception {
        String errorJsonBody = generateErrorJsonString("INTERNAL_ERROR", "Darwin bites the cable.");

        PowerMock.mockStaticPartial(YelpAPIErrors.class, "newError");
        PowerMock.expectPrivate(
                YelpAPIErrors.class,
                "newError",
                EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString()
        ).andThrow(new ReflectiveOperationException());

        PowerMock.replay(YelpAPIErrors.class);

        YelpAPIErrors.parseError(500, "INTERNAL_ERROR", errorJsonBody);
    }

    private String generateErrorJsonString(String errorId, String text) {
        String errorJsonStringFormat = "{\"error\": {\"id\": \"%s\", \"text\": \"%s\"}}";
        return String.format(errorJsonStringFormat, errorId, text);
    }
}
