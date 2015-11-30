package com.yelp.clientlib.connection;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface YelpAPI {

    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId);

    /**
     * TODO: This is a temporary endpoint to test Retrofit with query params. It will be refactored in the later
     * branches.
     */
    @GET("/v2/search")
    Call<SearchResponse> searchByLocation(@Query("term") String term, @Query("location") String location);

}

