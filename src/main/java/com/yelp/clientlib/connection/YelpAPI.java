package com.yelp.clientlib.connection;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BusinessOptions;
import com.yelp.clientlib.entities.options.SearchCoordinate;
import com.yelp.clientlib.entities.options.SearchLocation;
import com.yelp.clientlib.entities.options.SearchOptions;

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
     * @param params     Key, value pairs as business API params. Keys and values will be URL encoded by {@link
     *                   QueryMap}.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/business>https://www.yelp.com/developers/documentation/v2/business</a>
     */
    @GET("/v2/business/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId, @QueryMap Map<String, String> params);

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
     * @param params Key, value pairs as phone search API params. Keys and values will be URL encoded by {@link
     *               QueryMap}.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/phone_search>https://www.yelp.com/developers/documentation/v2/phone_search</a>
     */
    @GET("/v2/phone_search")
    Call<SearchResponse> getPhoneSearch(@Query("phone") String phone, @QueryMap Map<String, String> params);


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

