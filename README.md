# yelp-android
A Java library for the Yelp API. It simplifies the process of authentication, request construction, and response 
parsing for Java/Android developers using the Yelp API. This clientlib requires Java 7 or Android 2.3.

## Installation
// TODO

## Usage

### Basic usage
This library uses an API object to query against the API. Make an API object by using `YelpAPIFactory` to create a
`YelpAPI` with you API keys.
```
YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
YelpAPI yelpAPI = apiFactory.createAPI();
```

### [Search API](http://www.yelp.com/developers/documentation/v2/search_api)
Once you have a `YelpAPI` object you can use `search` to generate a `Call` object which makes a request to the Search API.

The general params and locale options should be passed to the method as a `Map<String, String>`. The full list of 
parameters can be found in the [Search API Documentation](https://www.yelp.com/developers/documentation/v2/search_api).
```
Map<String, String> params = new HashMap<>();

// general params
params.put("term", "food");
params.put("limit", "3");

// locale params
params.put("lang", "fr");

Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
```

Now you can execute the Call object to send the request.
```
Response<SearchResponse> response = call.execute();
```

You can also pass in a Callback object to send the request asynchronously. For more see [Asynchronous Requests Section](#asynchronous-requests).
```
Callback<SearchResponse> callback = new Callback<SearchResponse>() {
    @Override
    public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
        SearchResponse searchResponse = response.body();
        // Update UI text with the searchResponse.
    }
    @Override
    public void onFailure(Throwable t) {
        // HTTP error happened, do something to handle it.
    }
};

call.enqueue(callback);
```

Additionally there are two more search methods for searching by a [bounding box](https://www.yelp.com/developers/documentation/v2/search_api#searchGBB) or for [geographical coordinates](https://www.yelp.com/developers/documentation/v2/search_api#searchGC):
```
// bounding box
BoundingBoxOptions bounds = BoundingBoxOptions.builder()
        .swLatitude(37.7577)
        .swLongitude(-122.4376)
        .neLatitude(37.785381)
        .neLongitude(-122.391681).build();
Call<SearchResponse> call = yelpAPI.search(bounds, params);
Response<SearchResponse> response = call.execute();

// coordinates
CoordinateOptions coordinate = CoordinateOptions.builder()
        .latitude(37.7577)
        .longitude(-122.4376).build();
Call<SearchResponse> call = yelpAPI.search(coordinate, params);
Response<SearchResponse> response = call.execute();
```

### [Business API](http://www.yelp.com/developers/documentation/v2/business)
To query the Business API, use the `getBusiness` function with a business id. You can also pass in locale parameters 
in a `Map<String, String>` as specified in the [Business API Documentation](http://www.yelp.com/developers/documentation/v2/business).
```
Call<Business> call = yelpAPI.getBusiness("yelp-san-francisco");
Response<Business> response = call.execute();
```
You can pass in locale information as well.
```
Map<String, String> params = new HashMap<>();
params.put("lang", "fr");

Call<Business> call = yelpAPI.getBusiness("yelp-san-francisco", params);
Response<Business> response = call.execute();
```

### [Phone Search API](http://www.yelp.com/developers/documentation/v2/phone_search)
To query the Phone Search API, use the `getPhoneSearch` function with a phone number. Additional parameters can be
passed in by using a `Map<String, String>` as specified in the [Phone Search API Documentation](https://www.yelp.com/developers/documentation/v2/phone_search).
```
Call<SearchResponse> call = yelpAPI.getPhoneSearch("+15555555555");
Response<SearchResponse> response = call.execute();
```
You can pass in country code information as well
```
Map<String, String> params = new HashMap<>();
params.put("cc", "US");
params.put("category", "fashion");

Call<SearchResponse> call = yelpAPI.getPhoneSearch("5555555555", params);
Response<SearchResponse> response = call.execute();
```

### Asynchronous Requests
This library uses [Retrofit](http://square.github.io/retrofit/) as HTTP client, use `enqueue()` on a `Call` object to set a 
`Callback` function for an asynchronous request.
```
Callback<Business> callback = new Callback<Business>() {
    @Override
    public void onResponse(Response<Business> response, Retrofit retrofit) {
        Business business = response.body();
        // Update UI text with the result.
    }
    @Override
    public void onFailure(Throwable t) {
        // HTTP error happened. Do something to handle it.
    }
};

Call<Business> call = yelpAPI.getBusiness(businessId);
call.enqueue(callback);
```

You can cancel asynchronous requests by simply call cancel() on the Call objects.
```
Call<Business> call = yelpAPI.getBusiness(businessId);
call.enqueue(businessCallback);

// The activity is destroyed and the call should be canceled.
call.cancel();
```

For more information about the usage of asynchronous requests in Retrofit see [Retrofit documentation](http://square.github.io/retrofit/).

## Responses
After `Call` object is executed, a `Response` contains parsed Java objects will be returned, use `Response.body()` to 
get a parsed Java object.

Search and phone search responses are parsed into `SearchResponse` objects.
```
Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
SearchResponse searchResponse = call.execute().body();

int totalNumberOfResult = searchResponse.total();  // 3

ArrayList<Business> businesses = searchResponse.businesses();
String businessName = businesses.get(0).name();  // "JapaCurry Truck"
String rating = businesses.get(0).rating();  // 4.0
```

Business responses are parsed into `Business` objects directly.
```
Call<Business> call = yelpAPI.business("japacurry-truck-san-francisco");
Response<Business> response = call.execute();
Business business = response.body();

String businessName = business.name();  // "JapaCurry Truck"
String rating = business.rating();  // 4.0
```

For a full list of available response fields, take a look at the [documentation](https://www.yelp.com/developers/documentation/v2/overview) 
or the classes defined in [com.yelp.clientlib.entities](../tree/master/src/main/java/com/yelp/clientlib/entities).

## Contributing
1. Fork it ( http://github.com/yelp/yelp-android/fork )
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

//TODO: Add information about test suits.