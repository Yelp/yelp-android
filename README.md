# yelp-android
A Java library for the Yelp API. It simplifies the process of authentication, request construction, and response 
parsing for Java/Android developers using the Yelp API. This clientlib requires Java 7 or Android 2.3.

## Installation
We are working on putting this library into Maven central repository. Meanwhile, build it locally by using [Gradle](http://gradle.org/) 
for your other projects.

After git clone this project, build it locally.
```
gradle build
```

After build successfully, install the built library into your local Maven repository so it can be used by other 
projects.
```
gradle install
```

Add dependency to your project by adding the following lines into `build.gradle`.
```
// Add Maven local repository so your project can find local-built libraries.
repositories {
    mavenLocal()
}

// Add dependency to the library.
dependencies {
    ...
    compile 'com.yelp:yelp-android:1.0-SNAPSHOT'
    ...
}
```

## Usage

### Basic usage
This library uses an `YelpAPI` object to query against the API. Make an `YelpAPI` object by using `YelpAPIFactory` with 
your API keys.
```
YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
YelpAPI yelpAPI = apiFactory.createAPI();
```

### [Search API](http://www.yelp.com/developers/documentation/v2/search_api)
Once you have a `YelpAPI` object you can use the `search` function to generate a `Call` object which makes a request to 
the Search API.

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

Now you can execute the `Call` object to send the request.
```
Response<SearchResponse> response = call.execute();
```

You can also pass in a `Callback` object to send the request asynchronously. For more see [Asynchronous Requests](#asynchronous-requests) section.
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
This library uses [Retrofit](http://square.github.io/retrofit/) as HTTP client. To send a request asynchronously, use 
`Call.enqueue()` to set `Callback` function for an asynchronous request.
```
Callback<Business> callback = new Callback<Business>() {
    @Override
    public void onResponse(Response<Business> response, Retrofit retrofit) {
        Business business = response.body();
        // Update UI text with the Business object.
    }
    @Override
    public void onFailure(Throwable t) {
        // HTTP error happened, do something to handle it.
    }
};

Call<Business> call = yelpAPI.getBusiness(businessId);
call.enqueue(callback);
```

You can cancel asynchronous requests by simply call `cancel()` on `Call` objects. It is important to cancel your calls 
while your `Activity` is being destroyed to avoid memory leak.
```
Call<Business> call = yelpAPI.getBusiness(businessId);
call.enqueue(callback);

// Activity is being destroyed and the call should be canceled.
call.cancel();
```

For more information about the usage of asynchronous requests in Retrofit, see [Retrofit documentation](http://square.github.io/retrofit/).

## Responses
After `Call` object is executed, a `Response` contains parsed Java objects will be returned, use `Response.body()` to 
get parsed Java objects.

Search and phone search responses are parsed into `SearchResponse` objects.
```
Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
SearchResponse searchResponse = call.execute().body();

int totalNumberOfResult = searchResponse.total();  // 3

ArrayList<Business> businesses = searchResponse.businesses();
String businessName = businesses.get(0).name();  // "JapaCurry Truck"
Double rating = businesses.get(0).rating();  // 4.0
```

Business responses are parsed into `Business` objects directly.
```
Call<Business> call = yelpAPI.business("japacurry-truck-san-francisco");
Response<Business> response = call.execute();
Business business = response.body();

String businessName = business.name();  // "JapaCurry Truck"
Double rating = business.rating();  // 4.0
```

For a full list of available response fields, take a look at the [documentation](https://www.yelp.com/developers/documentation/v2/overview) 
or the classes defined in [com.yelp.clientlib.entities](../../tree/master/src/main/java/com/yelp/clientlib/entities).

## Contributing
1. Fork it ( http://github.com/yelp/yelp-android/fork )
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

## Testing
Please write tests for any new features. We use JUnit + Gradle so just run `gradle test` to run the full test suite. 
To know more about running JUnit tests in Gradle, see [Gradle: The Java Plugin - Test](https://docs.gradle
.org/current/userguide/java_plugin.html#sec:java_test).

If you are adding a new integration test, you will need to connect to the Yelp API. You can set this up by putting 
your API keys into `src/integration-test/resources/credentials.yaml` in the following format:
```
consumer_key: YOUR_CONSUMER_KEY
consumer_secret: YOUR_CONSUMER_SECRET
token: YOUR_TOKEN
token_secret: YOUR_TOKEN_SECRET
```

To run the integration tests, execute `gradle integrationTest`. Integration tests will not be ran in the build process
 by executing `gradle build`.