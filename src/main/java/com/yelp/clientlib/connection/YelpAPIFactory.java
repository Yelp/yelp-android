package com.yelp.clientlib.connection;

import okhttp3.OkHttpClient;
import com.yelp.clientlib.exception.ErrorHandlingInterceptor;

import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.Retrofit;
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

    private OkHttpClient httpClient;

    /**
     * Construct a new {@code YelpAPIFactory}.
     *
     * @param consumerKey    the consumer key.
     * @param consumerSecret the consumer secret.
     * @param token          the access token.
     * @param tokenSecret    the token secret.
     * @see <a href="https://www.yelp.com/developers/manage_api_keys=>https://www.yelp.com/developers/manage_api_keys</a>
     */
    public YelpAPIFactory(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(token, tokenSecret);

        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
    }

    /**
     * Initiate a {@link YelpAPI} instance.
     *
     * @return an instance of {@link YelpAPI}.
     */
    public YelpAPI createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(this.httpClient)
                .build();

        return retrofit.create(YelpAPI.class);
    }

    /**
     * Get the base URL of Yelp APIs.
     *
     * @return the base URL of Yelp APIs.
     */
    public String getAPIBaseUrl() {
        return YELP_API_BASE_URL;
    }
}

