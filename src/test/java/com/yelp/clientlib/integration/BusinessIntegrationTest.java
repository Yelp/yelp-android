package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.util.AsyncTestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
public class BusinessIntegrationTest {
    private String businessId = "yelp-san-francisco";
    private YelpAPI yelpAPI;

    @Before
    public void setUp() {
        yelpAPI = new YelpAPIFactory(
                Credential.getConsumerKey(),
                Credential.getConsumerSecret(),
                Credential.getToken(),
                Credential.getTokenSecret()
        ).createAPI();
    }

    @Test
    public void testGetBusiness() throws IOException {
        Business business = yelpAPI.getBusiness(businessId);

        Assert.assertNotNull(business);
        Assert.assertEquals(businessId, business.id());
    }

    @Test
    public void testGetBusinessAsynchronous() throws IOException, InterruptedException {
        final ArrayList<Business> returnedBusinessWrapper = new ArrayList<>();
        Callback<Business> businessCallback = new Callback<Business>() {
            @Override
            public void success(Business business, Response response) {
                returnedBusinessWrapper.add(business);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        };

        yelpAPI.getBusiness(businessId, businessCallback);
        AsyncTestUtil.waitAndCheckAsyncRequestStatus(returnedBusinessWrapper);

        Business business = returnedBusinessWrapper.get(0);
        Assert.assertNotNull(business);
        Assert.assertEquals(businessId, business.id());
    }
}
