package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
public class SearchIntegrationTest {

    @Test
    public void testSearchByLocation() throws IOException {
        String term = "food";
        String location = "San+Francisco";

        YelpAPI yelpAPI = new YelpAPIFactory(
                Credential.getConsumerKey(),
                Credential.getConsumerSecret(),
                Credential.getToken(),
                Credential.getTokenSecret()
        ).createAPI();

        SearchResponse searchResponse = yelpAPI.searchByLocation(term, location);

        Assert.assertNotNull(searchResponse);
    }
}
