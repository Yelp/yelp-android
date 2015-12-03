package com.yelp.clientlib.connection;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


/**
 * Util class to create YelpAPI as the stub to use Yelp API. This is the entry point to use this clientlib.
 * <p>
 * Example:<br />
 * YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);<br />
 * YelpAPI yelpAPI = apiFactory.createAPI();<br />
 * Business business = yelpAPI.getBusiness(businessId).execute();
 * </p>
 */
public class YelpAPIFactory {

    private static final String YELP_API_BASE_URL = "https://api.yelp.com";

    private OkClient client;
    private Converter converter;

    public YelpAPIFactory(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(token, tokenSecret);
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new SigningInterceptor(consumer));

        client = new OkClient(httpClient);
        converter = new JacksonConverter();
    }

    public YelpAPI createAPI() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getAPIBaseUrl())
                .setClient(this.client)
                .setConverter(this.converter)
                .build();

        return adapter.create(YelpAPI.class);
    }

    public String getAPIBaseUrl() {
        return YELP_API_BASE_URL;
    }
}

