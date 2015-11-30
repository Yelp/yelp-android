package com.yelp.clientlib.connection;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;


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

        Interceptor oAuthInterceptor = new OAuth10aInterceptor(consumerKey, consumerSecret, token, tokenSecret);
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(oAuthInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YELP_API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(YelpAPI.class);
    }
}

