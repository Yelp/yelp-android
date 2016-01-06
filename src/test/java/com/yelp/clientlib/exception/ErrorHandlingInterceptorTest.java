package com.yelp.clientlib.exception;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.InternalError;
import com.yelp.clientlib.exception.exceptions.UnexpectedAPIError;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Response.class, Protocol.class})
public class ErrorHandlingInterceptorTest {

    Interceptor errorHandlingInterceptor;

    @Before
    public void setUp() {
        this.errorHandlingInterceptor = new ErrorHandlingInterceptor();
    }

    /**
     * Ensure the interceptor does nothing besides proceeding the request if the request is done successfully.
     */
    @Test
    public void testSuccessfulRequestNotDoingAnythingExceptProceedingRequests() throws IOException {
        Request mockRequest = PowerMock.createMock(Request.class);
        Response mockResponse = PowerMock.createMock(Response.class);
        Interceptor.Chain mockChain = PowerMock.createMock(Interceptor.Chain.class);

        EasyMock.expect(mockChain.request()).andReturn(mockRequest);
        EasyMock.expect(mockChain.proceed(mockRequest)).andReturn(mockResponse);
        EasyMock.expect(mockResponse.isSuccessful()).andReturn(true);

        PowerMock.replay(mockRequest, mockResponse, mockChain);

        Response returnedResponse = errorHandlingInterceptor.intercept(mockChain);

        PowerMock.verify(mockChain);
        Assert.assertEquals(mockResponse, returnedResponse);
    }

    @Test
    public void testParseNullResponseBody() throws IOException {
        int errorCode = 500;
        String errorMessage = "Internal Server Error";

        Interceptor.Chain mockChain = mockChainWithErrorResponse(errorCode, errorMessage, null);
        try {
            errorHandlingInterceptor.intercept(mockChain);
        } catch (UnexpectedAPIError error) {
            verifyError(error, errorCode, errorMessage, null, null);
            return;
        }

        Assert.fail("Expected failure not returned.");
    }

    @Test
    public void testParseBusinessUnavailable() throws IOException {
        int errorCode = 400;
        String errorMessage = "Bad Request";
        String errorId = "BUSINESS_UNAVAILABLE";
        String errorText = "Business information is unavailable";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        Interceptor.Chain mockChain = mockChainWithErrorResponse(errorCode, errorMessage, errorJsonBody);
        try {
            errorHandlingInterceptor.intercept(mockChain);
        } catch (BusinessUnavailable error) {
            verifyError(error, errorCode, errorMessage, errorId, errorText);
            return;
        }

        Assert.fail("Expected failure not returned.");
    }

    @Test
    public void testParseInternalError() throws IOException {
        int errorCode = 500;
        String errorMessage = "Internal Server Error";
        String errorId = "INTERNAL_ERROR";
        String errorText = "Some internal error happened";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        Interceptor.Chain mockChain = mockChainWithErrorResponse(errorCode, errorMessage, errorJsonBody);
        try {
            errorHandlingInterceptor.intercept(mockChain);
        } catch (InternalError error) {
            verifyError(error, errorCode, errorMessage, errorId, errorText);
            return;
        }

        Assert.fail("Expected failure not returned.");
    }

    @Test
    public void testParseUnexpectedAPIError() throws IOException {
        int errorCode = 400;
        String errorMessage = "Bad Request";
        String errorId = "COULD_BE_ANY_THING_NOT_DEFINED";
        String errorText = "Woops, there is something unexpected happened";
        String errorJsonBody = generateErrorJsonString(errorId, errorText);

        Interceptor.Chain mockChain = mockChainWithErrorResponse(errorCode, errorMessage, errorJsonBody);
        try {
            errorHandlingInterceptor.intercept(mockChain);
        } catch (UnexpectedAPIError error) {
            verifyError(error, errorCode, errorMessage, errorId, errorText);
            return;
        }

        Assert.fail("Expected failure not returned.");
    }

    @Test(expected = IOException.class)
    public void testParseInvalidJsonBody() throws IOException {
        int errorCode = 500;
        String errorMessage = "Internal Server Error";
        String errorHTMLBody = "<html><title>This is not JSON</title></html>";

        Interceptor.Chain mockChain = mockChainWithErrorResponse(errorCode, errorMessage, errorHTMLBody);
        errorHandlingInterceptor.intercept(mockChain);
    }

    private Interceptor.Chain mockChainWithErrorResponse(
            int errorCode,
            String errorMessage,
            String errorBody
    ) throws IOException {
        Response response = new Response.Builder()
                .request(PowerMock.createMock(Request.class))
                .protocol(PowerMock.createMock(Protocol.class))
                .code(errorCode)
                .message(errorMessage)
                .body(errorBody != null ? ResponseBody.create(MediaType.parse("UTF-8"), errorBody) : null)
                .build();

        Interceptor.Chain mockChain = PowerMock.createMock(Interceptor.Chain.class);
        EasyMock.expect(mockChain.request()).andReturn(PowerMock.createMock(Request.class));
        EasyMock.expect(mockChain.proceed(EasyMock.anyObject(Request.class))).andReturn(response);
        PowerMock.replay(mockChain);

        return mockChain;
    }

    private void verifyError(
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

    private String generateErrorJsonString(String errorId, String text) {
        String errorJsonStringFormat = "{\"error\": {\"id\": \"%s\", \"text\": \"%s\"}}";
        return String.format(errorJsonStringFormat, errorId, text);
    }

}
