package com.yelp.clientlib.integration;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.SearchCoordinate;
import com.yelp.clientlib.entities.options.SearchLocation;
import com.yelp.clientlib.entities.options.SearchOptions;
import com.yelp.clientlib.util.AsyncTestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
public class SearchIntegrationTest {
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
    public void testSearchByLocationWithNoCoordinate() throws IOException {
        SearchOptions options = new SearchOptions();
        options.setTerm("yelp");

        Call<SearchResponse> call = yelpAPI.search("San Francisco", null, options);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }

    @Test
    public void testSearchByLocationWithCoordinate() throws IOException {
        SearchCoordinate coordinate = SearchCoordinate.builder()
                .latitude(37.7867703362929)
                .longitude(-122.399958372115).build();

        SearchOptions options = new SearchOptions();
        options.setTerm("yelp");

        Call<SearchResponse> call = yelpAPI.search("San Francisco", coordinate, options);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }


    @Test
    public void testSearchBySearchLocation() throws IOException {
        SearchLocation searchLocation = SearchLocation.builder()
                .latitude(37.7867703362929)
                .longitude(-122.399958372115).build();

        SearchOptions options = new SearchOptions();
        options.setTerm("yelp");

        Call<SearchResponse> call = yelpAPI.search(searchLocation, options);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }
}
