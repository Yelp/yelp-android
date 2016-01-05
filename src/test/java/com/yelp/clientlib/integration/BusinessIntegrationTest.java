package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.exception.exceptions.YelpAPIError;
import com.yelp.clientlib.util.AsyncTestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
public class BusinessIntegrationTest {
    private String businessId = "yelp-san-francisco";
    private YelpAPI yelpAPI;

    @Before
    public void setUp() {
        YelpAPIFactory yelpAPIFactory = new YelpAPIFactory(
                Credential.getConsumerKey(),
                Credential.getConsumerSecret(),
                Credential.getToken(),
                Credential.getTokenSecret()
        );

        // Make API requests to be executed in main thread so we can verify it easily.
        yelpAPIFactory = AsyncTestUtil.setToRunInMainThread(yelpAPIFactory);

        yelpAPI = yelpAPIFactory.createAPI();
    }

    @Test
    public void testGetBusiness() throws IOException {
        Call<Business> call = yelpAPI.getBusiness(businessId);
        Response<Business> response = call.execute();
        Assert.assertEquals(200, response.code());

        Business business = response.body();
        Assert.assertNotNull(business);
        Assert.assertEquals(businessId, business.id());
    }

    @Test
    public void testGetBusinessAsynchronous() {
        final ArrayList<Response<Business>> responseWrapper = new ArrayList<>();
        Callback<Business> businessCallback = new Callback<Business>() {
            @Override
            public void onResponse(Response<Business> response, Retrofit retrofit) {
                responseWrapper.add(response);
            }

            @Override
            public void onFailure(Throwable t) {
                Assert.fail("Unexpected failure: " + toString());
            }
        };

        Call<Business> call = yelpAPI.getBusiness(businessId);
        call.enqueue(businessCallback);

        Response<Business> response = responseWrapper.get(0);
        Assert.assertEquals(200, response.code());

        Business business = response.body();
        Assert.assertNotNull(business);
        Assert.assertEquals(businessId, business.id());
    }

    @Test
    public void testGetBusinessWith400Response() throws IOException {
        Call<Business> call = yelpAPI.getBusiness("I-dont-think-this-biz-really-exists");

        try {
            call.execute();
        } catch (YelpAPIError apiError) {
            Assert.assertEquals(400, apiError.getCode());
            Assert.assertEquals("Bad Request", apiError.getMessage());
            Assert.assertEquals("BUSINESS_UNAVAILABLE", apiError.getErrorId());
            Assert.assertTrue(apiError.getText().contains("Business information is unavailable"));
        }
    }

    @Test
    public void testGetBusinessAsynchronousWith400Response() throws IOException {
        Callback<Business> businessCallback = new Callback<Business>() {
            @Override
            public void onResponse(Response<Business> response, Retrofit retrofit) {
                Assert.fail("Expected failure not returned.");
            }

            @Override
            public void onFailure(Throwable t) {
                Assert.assertTrue(t instanceof BusinessUnavailable);

                YelpAPIError apiError = (YelpAPIError) t;
                Assert.assertEquals(400, apiError.getCode());
                Assert.assertEquals("Bad Request", apiError.getMessage());
                Assert.assertEquals("BUSINESS_UNAVAILABLE", apiError.getErrorId());
                Assert.assertTrue(apiError.getText().contains("Business information is unavailable"));
            }
        };

        Call<Business> call = yelpAPI.getBusiness("I-dont-think-this-biz-really-exists");
        call.enqueue(businessCallback);
    }
}
