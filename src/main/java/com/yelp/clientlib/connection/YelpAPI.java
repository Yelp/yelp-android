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

    /**
     * Make a request to the business endpoint.
     *
     * @param businessId The business id.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/business>https://www.yelp.com/developers/documentation/v2/business</a>
     */
    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId);

    /**
     * Make a request to the business endpoint.
     *
     * @param businessId The business id.
     * @param params     Key, value pairs as business API params. Key and value will be URL encoded by {@link
     *                   QueryMap}.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/business>https://www.yelp.com/developers/documentation/v2/business</a>
     */
    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId, @QueryMap Map<String, String> params);

    /**
     * TODO: This is a temporary endpoint to test Retrofit with query params. It will be refactored in later branches.
     */
    @GET("/v2/search")
    Call<SearchResponse> searchByLocation(@Query("term") String term, @Query("location") String location);

}

