package com.yelp.clientlib.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.JsonTestUtils;
import com.yelp.clientlib.entities.SearchResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class YelpAPITest {

    private MockWebServer mockServer;
    private YelpAPI yelpAPI;

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
        yelpAPI = yelpAPIFactory.createAPI();
    }

    @After
    public void teardown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testGetBusiness() throws InterruptedException, IOException {
        JsonNode businessJsonNode = JsonTestUtils.getBusinessResponseJsonNode();
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(businessJsonNode.toString());
        mockServer.enqueue(mockResponse);

        Business business = yelpAPI.getBusiness("test-business-id");

        RecordedRequest recordedRequest = mockServer.takeRequest();
        verifyAuthorizationHeader(recordedRequest.getHeaders().get("Authorization"));

        // Verify basic HTTP elements.
        Assert.assertEquals("GET", recordedRequest.getMethod());
        Assert.assertEquals("/v2/business/test-business-id", recordedRequest.getPath());
        Assert.assertEquals(0, recordedRequest.getBodySize());

        // Verify the response is deserialized.
        Assert.assertEquals(businessJsonNode.path("id").textValue(), business.id());
    }

    @Test
    public void testSearchByLocation() throws IOException, InterruptedException {
        JsonNode searchResponseJsonNode = JsonTestUtils.getSearchResponseJsonNode();
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(searchResponseJsonNode.toString());
        mockServer.enqueue(mockResponse);

        SearchResponse searchResponse = yelpAPI.searchByLocation("food", "Boston");

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
