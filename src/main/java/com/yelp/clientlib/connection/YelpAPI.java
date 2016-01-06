package com.yelp.clientlib.connection;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BusinessOptions;
import com.yelp.clientlib.entities.options.SearchCoordinate;
import com.yelp.clientlib.entities.options.SearchLocation;
import com.yelp.clientlib.entities.options.SearchOptions;

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


    @GET("/v2/search")
    Call<SearchResponse> search(
            @Query("location") String location,
            @Query("cll") SearchCoordinate coordinate,
            @QueryMap SearchOptions options
    );

    @GET("/v2/search")
    Call<SearchResponse> search(@Query("ll") SearchLocation location, @QueryMap SearchOptions options);

    //TODO: Add search with BoundingBox
}

