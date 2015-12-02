package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class BusinessIntegrationTest {

    @Test
    public void testGetBusiness() throws IOException {
        String businessId = "yelp-san-francisco";

        YelpAPI yelpAPI = YelpAPIFactory.createAPI(
                Credential.getConsumerKey(),
                Credential.getConsumerSecret(),
                Credential.getToken(),
                Credential.getTokenSecret()
        );

        Business business = yelpAPI.getBusiness(businessId);

        Assert.assertNotNull(business);
        Assert.assertEquals(businessId, business.id());
    }
}
