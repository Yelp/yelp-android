package com.yelp.clientlib;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BoundingBoxOptions;
import com.yelp.clientlib.entities.options.CoordinateOptions;
import com.yelp.clientlib.utils.AsyncTestUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
public class SearchIntegrationTest {
    private YelpAPI yelpAPI;

    @Before
    public void setUp() {
        YelpAPIFactory yelpAPIFactory = new YelpAPIFactory(
                Credential.consumerKey(),
                Credential.consumerSecret(),
                Credential.token(),
                Credential.tokenSecret()

        );

        // Make API requests to be executed in main thread so we can verify it easily.
        yelpAPIFactory = AsyncTestUtils.setToRunInMainThread(yelpAPIFactory);

        yelpAPI = yelpAPIFactory.createAPI();
    }

    @Test
    public void testSearchByLocation() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("term", "yelp");

        Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }

    @Test
    public void testSearchByLocationWithOptionalCoordinate() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("term", "yelp");
        params.put("cll", "37.7867703362929,-122.399958372115");

        Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }


    @Test
    public void testSearchByCoordinateOptions() throws IOException {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(37.7867703362929)
                .longitude(-122.399958372115).build();

        Map<String, String> params = new HashMap<>();
        params.put("term", "yelp");

        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }

    @Test
    public void testSearchByBoundingBoxOptions() throws IOException {
        BoundingBoxOptions bounds = BoundingBoxOptions.builder()
                .swLatitude(37.900000)
                .swLongitude(-122.500000)
                .neLatitude(37.788022)
                .neLongitude(-122.399797)
                .build();

        Map<String, String> params = new HashMap<>();
        params.put("term", "yelp");

        Call<SearchResponse> call = yelpAPI.search(bounds, params);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
    }
}
