package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIGenerator;
import com.yelp.clientlib.entities.Business;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit.Response;

public class BusinessIntegrationTest {

    @Test
    public void testGetBusiness() throws IOException {
        String businessId = "yelp-san-francisco";

        YelpAPI yelpAPI = YelpAPIGenerator.createAPIStub(
                Credential.getConsumerKey(),
                Credential.getConsumerSecret(),
                Credential.getToken(),
                Credential.getTokenSecret()
        );

        Response<Business> response = yelpAPI.getBusiness(businessId).execute();
        Business business = response.body();

        Assert.assertNotNull(business);
        Assert.assertEquals(businessId, business.id());
    }
}
