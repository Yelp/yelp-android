package com.yelp.clientlib.exception;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
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
@PrepareForTest({YelpAPIErrors.class, Request.class, Response.class})
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

    /**
     * Ensure the interceptor raises exceptions if requests are failed.
     */
    @Test
    public void testFailedRequestsRaiseException() throws IOException {
        PowerMock.mockStatic(YelpAPIErrors.class);
        Request mockRequest = PowerMock.createMock(Request.class);
        Response mockResponse = PowerMock.createNiceMock(Response.class);
        Interceptor.Chain mockChain = PowerMock.createMock(Interceptor.Chain.class);
        YelpAPIError mockYelpAPIError = PowerMock.createMock(YelpAPIError.class);

        EasyMock.expect(mockChain.request()).andReturn(mockRequest);
        EasyMock.expect(mockChain.proceed(mockRequest)).andReturn(mockResponse);
        EasyMock.expect(mockResponse.isSuccessful()).andReturn(false);

        EasyMock.expect(
                YelpAPIErrors.parseError(EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyString())
        ).andReturn(mockYelpAPIError);

        PowerMock.replay(mockChain, mockResponse, mockYelpAPIError, YelpAPIErrors.class);

        try {
            errorHandlingInterceptor.intercept(mockChain);
        } catch (YelpAPIError apiError) {
            PowerMock.verify(mockChain, mockResponse, mockYelpAPIError, YelpAPIErrors.class);
            Assert.assertEquals(mockYelpAPIError, apiError);
        }
    }
}
