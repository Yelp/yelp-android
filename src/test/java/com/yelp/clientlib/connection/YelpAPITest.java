package com.yelp.clientlib.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.JsonTestUtils;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;
import com.yelp.clientlib.exception.exceptions.BusinessUnavailable;
import com.yelp.clientlib.util.AsyncTestUtil;
import com.yelp.clientlib.util.ErrorTestUtil;

import org.junit.After;
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
        setUpMockServerResponse(200, "OK", businessJsonNode.toString());

        Call<Business> call = yelpAPI.getBusiness(testBusinessId);
        Business business = call.execute().body();

        verifyRequestForGetBusiness(testBusinessId);
        verifyResponseDeserializationForGetBusiness(business);
    }

    @Test
    public void testGetBusinessAsynchronous() throws InterruptedException {
        String testBusinessId = "test-business-id";
        setUpMockServerResponse(200, "OK", businessJsonNode.toString());

        final ArrayList<Business> returnedBusinessWrapper = new ArrayList<>();
        Callback<Business> businessCallback = new Callback<Business>() {
            @Override
            public void onResponse(Response<Business> response, Retrofit retrofit) {
                returnedBusinessWrapper.add(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Assert.fail("Unexpected failure: " + t.toString());
            }
        };

        Call<Business> call = yelpAPI.getBusiness(testBusinessId);
        call.enqueue(businessCallback);

        verifyRequestForGetBusiness(testBusinessId);
        verifyResponseDeserializationForGetBusiness(returnedBusinessWrapper.get(0));
    }

    @Test
    public void testGetBusinessWithParams() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", businessJsonNode.toString());

        String testBusinessId = "test-business-id";
        Map<String, String> params = new HashMap<>();
        params.put("cc", "US");
        params.put("lang", "en");
        params.put("lang_filter", "true");
        params.put("actionlinks", "true");

        Call<Business> call = yelpAPI.getBusiness(testBusinessId, params);
        Business business = call.execute().body();

        verifyRequestForGetBusiness(testBusinessId, params);
        verifyResponseDeserializationForGetBusiness(business);
    }

    @Test
    public void testGetBusinessParamsBeURLEncoded() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", businessJsonNode.toString());

        String testBusinessId = "test-business-id";
        Map<String, String> params = new HashMap<>();
        String key = "the key";
        String value = "the value";
        params.put(key, value);
        String expectedEncodedParamString = "the%20key=the%20value";

        Call<Business> call = yelpAPI.getBusiness(testBusinessId, params);
        call.execute().body();

        RecordedRequest recordedRequest = mockServer.takeRequest();
        Assert.assertTrue(recordedRequest.getPath().contains(expectedEncodedParamString));
    }

    @Test
    public void testGetBusinessWithEmptyParams() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", businessJsonNode.toString());

        String testBusinessId = "test-business-id";
        Call<Business> call = yelpAPI.getBusiness(testBusinessId, new HashMap<String, String>());
        Business business = call.execute().body();

        verifyRequestForGetBusiness(testBusinessId);
        verifyResponseDeserializationForGetBusiness(business);
    }

    @Test
    public void testGetBusinessWithNullParams() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", businessJsonNode.toString());

        String testBusinessId = "test-business-id";

        Call<Business> call = yelpAPI.getBusiness(testBusinessId, null);
        Business business = call.execute().body();

        verifyRequestForGetBusiness(testBusinessId);
        verifyResponseDeserializationForGetBusiness(business);
    }

    @Test
    public void testGetPhoneSearch() throws IOException, InterruptedException {
        String testPhone = "1234567899";
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(testPhone);
        SearchResponse searchResponse = call.execute().body();

        verifyRequestForGetPhoneSearch(testPhone);
        verifyResponseDeserializationForSearchResponse(searchResponse);
    }

    @Test
    public void testGetPhoneSearchAsynchronous() throws InterruptedException {
        String testPhone = "1234567899";
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        final ArrayList<SearchResponse> responseWrapper = new ArrayList<>();
        Callback<SearchResponse> businessCallback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                responseWrapper.add(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Assert.fail("Unexpected failure: " + t.toString());
            }
        };

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(testPhone);
        call.enqueue(businessCallback);

        verifyRequestForGetPhoneSearch(testPhone);
        verifyResponseDeserializationForSearchResponse(responseWrapper.get(0));
    }

    @Test
    public void testGetPhoneSearchWithParams() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        String testPhone = "1234567899";
        Map<String, String> params = new HashMap<>();
        params.put("category", "restaurant");
        params.put("cc", "US");

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(testPhone, params);
        SearchResponse searchResponse = call.execute().body();

        verifyRequestForGetPhoneSearch(testPhone, params);
        verifyResponseDeserializationForSearchResponse(searchResponse);
    }

    @Test
    public void testGetPhoneSearchWithEmptyParams() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        String testPhone = "1234567899";
        Map<String, String> params = new HashMap<>();

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(testPhone, params);
        SearchResponse searchResponse = call.execute().body();

        verifyRequestForGetPhoneSearch(testPhone);
        verifyResponseDeserializationForSearchResponse(searchResponse);
    }

    @Test
    public void testGetPhoneSearchWithNullParams() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        String testPhone = "1234567899";

        Call<SearchResponse> call = yelpAPI.getPhoneSearch(testPhone, null);
        SearchResponse searchResponse = call.execute().body();

        verifyRequestForGetPhoneSearch(testPhone);
        verifyResponseDeserializationForSearchResponse(searchResponse);
    }

    @Test
    public void testGetBusiness400Response() throws IOException, InterruptedException {
        String testBusinessId = "test-business-id";
        String errorResponseBodyString = JsonTestUtils.getJsonNodeFromFile("sampleFailureResponse.json").toString();
        setUpMockServerResponse(400, "Bad Request", errorResponseBodyString);

        Call<Business> call = yelpAPI.getBusiness(testBusinessId);
        try {
            call.execute().body();
        } catch (BusinessUnavailable e) {
            ErrorTestUtil.verifyErrorContent(
                    e,
                    400,
                    "Bad Request",
                    "BUSINESS_UNAVAILABLE",
                    "Business information is unavailable"
            );
        }
    }

    @Test
    public void testSearchByLocation() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        Map<String, String> params = new HashMap<>();
        params.put("term", "yelp");

        Call<SearchResponse> call = yelpAPI.search("Boston", params);
        SearchResponse searchResponse = call.execute().body();

        Map<String, String> expectedCalledParams = new HashMap<>(params);
        expectedCalledParams.put("location", "Boston");

        verifyRequestForSearch(expectedCalledParams);
        verifyResponseDeserializationForSearchResponse(searchResponse);
    }

    @Test
    public void testSearchByCoordinateOptions() throws IOException, InterruptedException {
        setUpMockServerResponse(200, "OK", searchResponseJsonNode.toString());

        Map<String, String> params = new HashMap<>();
        params.put("term", "yelp");

        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(11.111111)
                .longitude(22.222222)
                .build();

        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
        SearchResponse searchResponse = call.execute().body();

        Map<String, String> expectedCalledParams = new HashMap<>(params);
        expectedCalledParams.put("ll", "11.111111,22.222222");
        verifyRequestForSearch(expectedCalledParams);
        verifyResponseDeserializationForSearchResponse(searchResponse);
    }


    private void setUpMockServerResponse(int responseCode, String responseMessage, String responseBody) {
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(responseBody)
                .setStatus(String.format("HTTP/1.1 %s %s", responseCode, responseMessage));
        mockServer.enqueue(mockResponse);
    }

    private void verifyRequestForGetBusiness(String businessId) throws InterruptedException {
        verifyRequestForGetBusiness(businessId, null);
    }

    private void verifyRequestForGetBusiness(String businessId, Map<String, String> params)
            throws InterruptedException {
        verifyRequest("/v2/business/" + businessId, params);
    }

    private void verifyRequestForGetPhoneSearch(String phone) throws InterruptedException {
        verifyRequestForGetPhoneSearch(phone, null);
    }

    private void verifyRequestForGetPhoneSearch(String phone, Map<String, String> params) throws InterruptedException {
        params = (params == null) ? new HashMap<String, String>() : new HashMap<>(params);
        params.put("phone", phone);

        verifyRequest("/v2/phone_search", params);
    }

    private void verifyRequestForSearch(Map<String, String> params) throws InterruptedException {
        verifyRequest("/v2/search", params);
    }

    private void verifyRequest(String pathPrefix, Map<String, String> params) throws InterruptedException {
        RecordedRequest recordedRequest = mockServer.takeRequest();
        verifyAuthorizationHeader(recordedRequest.getHeaders().get("Authorization"));

        Assert.assertEquals("GET", recordedRequest.getMethod());

        String path = recordedRequest.getPath();
        Assert.assertTrue(path.startsWith(pathPrefix));
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                Assert.assertTrue(path.contains(param.getKey() + "=" + param.getValue()));
            }
        }

        Assert.assertEquals(0, recordedRequest.getBodySize());
    }

    private void verifyResponseDeserializationForGetBusiness(Business business) {
        Assert.assertEquals(businessJsonNode.path("id").textValue(), business.id());
    }

    private void verifyResponseDeserializationForSearchResponse(SearchResponse searchResponse) {
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
