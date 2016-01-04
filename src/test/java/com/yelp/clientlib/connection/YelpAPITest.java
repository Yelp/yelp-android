package com.yelp.clientlib.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.JsonTestUtils;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.util.AsyncTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class YelpAPITest {
    private MockWebServer mockServer;
    private YelpAPI yelpAPI;
    private JsonNode businessJsonNode;
    private JsonNode searchResponseJsonNode;

    @Before
    public void setup() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();

        // Use TestAPIFactory so the requests are sent to the mock web server.
        YelpAPIFactory yelpAPIFactory = new TestAPIFactory(
                "consumerKey",
                "consumerSecret",
                "token",
                "tokenSecret",
                mockServer.url("/").toString()
        );

        // Make API requests to be executed in main thread so we can verify it easily.
        yelpAPIFactory = AsyncTestUtil.setToRunInMainThread(yelpAPIFactory);

        yelpAPI = yelpAPIFactory.createAPI();

        businessJsonNode = JsonTestUtils.getBusinessResponseJsonNode();
        searchResponseJsonNode = JsonTestUtils.getSearchResponseJsonNode();
    }

    @After
    public void teardown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testGetBusiness() throws IOException, InterruptedException {
        String testBusinessId = "test-business-id";
        setUpMockServer(businessJsonNode.toString());

        Call<Business> call = yelpAPI.getBusiness(testBusinessId);
        Business business = call.execute().body();

        verifyRequestForGetBusiness(testBusinessId);
        verifyResponseDeserializationForGetBusiness(business);
    }

    @Test
    public void testGetBusinessAsynchronous() throws IOException, InterruptedException {
        String testBusinessId = "test-business-id";
        setUpMockServer(businessJsonNode.toString());

        final ArrayList<Business> returnedBusinessWrapper = new ArrayList<>();
        Callback<Business> businessCallback = new Callback<Business>() {
            @Override
            public void onResponse(Response<Business> response, Retrofit retrofit) {
                returnedBusinessWrapper.add(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        Call<Business> call = yelpAPI.getBusiness(testBusinessId);
        call.enqueue(businessCallback);

        verifyRequestForGetBusiness(testBusinessId);
        verifyResponseDeserializationForGetBusiness(returnedBusinessWrapper.get(0));
    }

    @Test
    public void testSearchByLocation() throws IOException, InterruptedException {
        setUpMockServer(searchResponseJsonNode.toString());

        Call<SearchResponse> call = yelpAPI.searchByLocation("food", "Boston");
        SearchResponse searchResponse = call.execute().body();

        RecordedRequest recordedRequest = mockServer.takeRequest();
        verifyAuthorizationHeader(recordedRequest.getHeaders().get("Authorization"));

        // Verify basic HTTP elements.
        Assert.assertEquals("GET", recordedRequest.getMethod());
        String path = recordedRequest.getPath();
        Assert.assertTrue(path.startsWith("/v2/search"));
        Assert.assertTrue(path.contains("term=food"));
        Assert.assertTrue(path.contains("location=Boston"));
        Assert.assertEquals(0, recordedRequest.getBodySize());

        // Verify the deserialized response.
        Assert.assertEquals(new Integer(searchResponseJsonNode.path("total").asInt()), searchResponse.total());
    }

    private void setUpMockServer(String responseBody) {
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(responseBody);
        mockServer.enqueue(mockResponse);
    }

    private void verifyRequestForGetBusiness(String businessId) throws InterruptedException {
        RecordedRequest recordedRequest = mockServer.takeRequest();
        verifyAuthorizationHeader(recordedRequest.getHeaders().get("Authorization"));

        Assert.assertEquals("GET", recordedRequest.getMethod());
        Assert.assertEquals("/v2/business/" + businessId, recordedRequest.getPath());
        Assert.assertEquals(0, recordedRequest.getBodySize());
    }

    private void verifyResponseDeserializationForGetBusiness(Business business) {
        Assert.assertEquals(businessJsonNode.path("id").textValue(), business.id());
    }

    private void verifyAuthorizationHeader(String authHeader) {
        Assert.assertNotNull(authHeader);
        Assert.assertTrue(authHeader.contains("oauth_consumer_key"));
        Assert.assertTrue(authHeader.contains("oauth_nonce"));
        Assert.assertTrue(authHeader.contains("oauth_signature_method"));
        Assert.assertTrue(authHeader.contains("oauth_signature"));
        Assert.assertTrue(authHeader.contains("oauth_timestamp"));
    }

    /**
     * APIFactory which API base url can be set. Set apiBaseUrl to a mocked web server so requests are directed to it.
     */
    class TestAPIFactory extends YelpAPIFactory {
        private String apiBaseUrl;

        public TestAPIFactory(
                String consumerKey,
                String consumerSecret,
                String token,
                String tokenSecret,
                String apiBaseUrl
        ) {
            super(consumerKey, consumerSecret, token, tokenSecret);
            this.apiBaseUrl = apiBaseUrl;
        }

        @Override
        public String getAPIBaseUrl() {
            return apiBaseUrl;
        }
    }
}
