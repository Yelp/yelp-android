package com.yelp.clientlib.connection;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


/**
 * Util class to create YelpAPI as the stub to use Yelp API. This is the entry point to use this clientlib.
 * <p>
 * Example:<br />
 * YelpAPI yelpAPI = YelpAPIFactory.createAPI(consumerKey, consumerSecret, token, tokenSecret);<br />
 * Response<Business> response = yelpAPI.getBusiness(businessId).execute();
 * </p>
 */
public class YelpAPIFactory {

    public static String YELP_API_BASE_URL = "https://api.yelp.com";

    public static YelpAPI createAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(token, tokenSecret);
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new SigningInterceptor(consumer));

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(YELP_API_BASE_URL)
                .setClient(new OkClient(httpClient))
                .setConverter(new JacksonConverter())
                .build();

        return adapter.create(YelpAPI.class);
    }
}

