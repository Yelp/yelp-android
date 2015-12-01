package com.yelp.clientlib.connection;

import com.squareup.okhttp.OkHttpClient;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


/**
 * Util class to create YelpAPI as the stub to use Yelp API. This is the entry point to use this clientlib.
 * <p>
 * Example:<br />
 * YelpAPI yelpAPI = YelpAPIGenerator.createAPIStub(consumerKey, consumerSecret, token, tokenSecret);<br />
 * Response<Business> response = yelpAPI.getBusiness(businessId).execute();
 * </p>
 */
public class YelpAPIGenerator {

    public static String YELP_API_BASE_URL = "https://api.yelp.com";

    public static YelpAPI createAPIStub(String consumerKey, String consumerSecret, String token, String tokenSecret) {

        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(token, tokenSecret);
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new SigningInterceptor(consumer));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YELP_API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(YelpAPI.class);
    }
}

