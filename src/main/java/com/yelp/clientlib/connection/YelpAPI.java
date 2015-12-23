package com.yelp.clientlib.connection;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.BusinessOptions;
import com.yelp.clientlib.entities.SearchResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface YelpAPI {

    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId);


    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId, @QueryMap BusinessOptions options);

    /**
     * TODO: This is a temporary endpoint to test Retrofit with query params. It will be refactored in later branches.
     */
    @GET("/v2/search")
    Call<SearchResponse> searchByLocation(@Query("term") String term, @Query("location") String location);

}

