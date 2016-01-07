package com.yelp.clientlib.connection;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface YelpAPI {

    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId);

    /**
     * Make a request to the phone search endpoint.
     *
     * @param phone Business phone number to search for.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/phone_search>https://www.yelp.com/developers/documentation/v2/phone_search</a>
     */
    @GET("/v2/phone_search")
    Call<SearchResponse> getPhoneSearch(@Query("phone") String phone);

    /**
     * Make a request to the phone search endpoint.
     *
     * @param phone  Business phone number to search for.
     * @param params Key, value pairs as phone search API params. Key and value will be URL encoded by {@link
     *               QueryMap}.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/phone_search>https://www.yelp.com/developers/documentation/v2/phone_search</a>
     */
    @GET("/v2/phone_search")
    Call<SearchResponse> getPhoneSearch(@Query("phone") String phone, @QueryMap Map<String, String> params);

    /**
     * TODO: This is a temporary endpoint to test Retrofit with query params. It will be refactored in later branches.
     */
    @GET("/v2/search")
    Call<SearchResponse> searchByLocation(@Query("term") String term, @Query("location") String location);

}

