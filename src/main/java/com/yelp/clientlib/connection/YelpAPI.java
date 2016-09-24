package com.yelp.clientlib.connection;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BoundingBoxOptions;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    /**
     * Make a request to the search endpoint. Specify a location by neighborhood, address, or city.
     *
     * @param location Location by neighborhood, address, or city.
     * @param params   Key, value pairs as search API params. Keys and values will be URL encoded by {@link QueryMap}.
     * @return Object to execute the request.
     * @see <a href = https://www.yelp.com/developers/documentation/v2/search_api#searchNAC>https://www.yelp.com/developers/documentation/v2/search_api#searchNAC</a>
     */
    @GET("/v2/search")
    Call<SearchResponse> search(@Query("location") String location, @QueryMap Map<String, String> params);

    /**
     * Make a request to the search endpoint by geographic coordinate. Specify a latitude and longitude with optional
     * accuracy, altitude, and altitude_accuracy in {@link CoordinateOptions}.
     *
     * @param coordinate Geographic coordinate to search near.
     * @param params     Key, value pairs as search API params. Keys and values will be URL encoded by {@link QueryMap}.
     * @return Object to execute the request.
     * @see <a href = http://www.yelp.com/developers/documentation/v2/search_api#searchGC>http://www.yelp.com/developers/documentation/v2/search_api#searchGC</a>
     */
    @GET("/v2/search")
    Call<SearchResponse> search(@Query("ll") CoordinateOptions coordinate, @QueryMap Map<String, String> params);

    /**
     * Make a request to the search endpoint by bounding box. Specify a southwest latitude/longitude and a northeast
     * latitude/longitude in {@link BoundingBoxOptions}.
     *
     * <p>{@link BoundingBoxOptions} is already encoded in {@link BoundingBoxOptions#toString()} for the special URI
     * character it uses, "encoded" is set to true so Retrofit doesn't encode it again.
     *
     * @param boundingBox Geographical bounding box to search in.
     * @param params      Key, value pairs as search API params. Keys and values will be URL encoded by {@link QueryMap}.
     * @return Object to execute the request.
     * @see <a href = http://www.yelp.com/developers/documentation/v2/search_api#searchGBB>http://www.yelp.com/developers/documentation/v2/search_api#searchGBB</a>
     */
    @GET("/v2/search")
    Call<SearchResponse> search(
            @Query(value = "bounds", encoded = true) BoundingBoxOptions boundingBox,
            @QueryMap Map<String, String> params
    );
}

