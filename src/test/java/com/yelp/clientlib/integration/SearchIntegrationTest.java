package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIGenerator;
import com.yelp.clientlib.entities.SearchResponse;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit.Response;

public class SearchIntegrationTest {

    @Test
    public void testSearchByLocation() throws IOException {
        String term = "food";
        String location = "San+Francisco";

        YelpAPI yelpAPI = YelpAPIGenerator.createAPIStub(
                Credential.getConsumerKey(),
                Credential.getConsumerSecret(),
                Credential.getToken(),
                Credential.getTokenSecret()
        );

        Response<SearchResponse> response = yelpAPI.searchByLocation(term, location).execute();
        SearchResponse searchResponse = response.body();

        Assert.assertNotNull(searchResponse);
    }
}
