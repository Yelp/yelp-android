package com.yelp.clientlib;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.utils.AsyncTestUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
public class PhoneSearchIntegrationTest {
    private String phone = "+14159083801";
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
    public void testGetPhoneSearch() throws IOException {
        Call<SearchResponse> call = yelpAPI.getPhoneSearch(phone);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
        Business business = searchResponse.businesses().get(0);
        Assert.assertEquals(phone, business.phone());
    }

    @Test
    public void testGetPhoneSearchWithParams() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("category", "massmedia");
        params.put("cc", "US");

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(phone, params);
        Response<SearchResponse> response = call.execute();
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);
        Business business = searchResponse.businesses().get(0);
        Assert.assertEquals(phone, business.phone());
    }

    @Test
    public void testGetPhoneSearchAsynchronous() {
        final ArrayList<Response<SearchResponse>> responseWrapper = new ArrayList<>();
        Callback<SearchResponse> searchCallback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                responseWrapper.add(response);
            }

            @Override
            public void onFailure(Throwable t) {
                Assert.fail("Unexpected failure: " + t.toString());
            }
        };

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(phone);
        call.enqueue(searchCallback);

        Response<SearchResponse> response = responseWrapper.get(0);
        Assert.assertEquals(200, response.code());

        SearchResponse searchResponse = response.body();
        Assert.assertNotNull(searchResponse);

        Business business = searchResponse.businesses().get(0);
        Assert.assertEquals(phone, business.phone());
    }
}
