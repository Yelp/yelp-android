# yelp-android
A Java library for the Yelp API. 

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
Once you have a yelpAPI you can use `search` to make a request to the Search API.
```
yelpAPI.search('San Francisco');
```
You can also pass in general params and locale options to the method as a `Map<String, String>`.

```
Map<String, String> params = new HashMap<>();

// General params
params.put("term", "food");
params.put("limit", "3");

// Locale params
params.put("lang", "fr");

yelpAPI.search('San Francisco', params);
```

The full list of parameters can be found in the [Search API Documentation](https://www.yelp.com/developers/documentation/v2/search_api).

Additionally there are two more search methods for searching by a bounding box or for geographical coordinates:
```
BoundingBoxOptions bounds = BoundingBoxOptions.builder()
        .swLatitude(37.7577)
        .swLongitude(-122.4376)
        .neLatitude(37.785381)
        .neLongitude(-122.391681).build();

yelpAPI.search(bounds, params)

CoordinateOptions coordinate = CoordinateOptions.builder()
        .latitude(37.7577)
        .longitude(-122.4376).build();

yelpAPI.search(coordinate, params)
```

### [Business API](http://www.yelp.com/developers/documentation/v2/business)
To query the Business API, use the `getBusiness` function with a business id. You can also pass in locale parameters in
 a `Map<String, String>` as specified in the [Business API Documentation](http://www.yelp.com/developers/documentation/v2/business).
```
yelpAPI.getBusiness("yelp-san-francisco");
```
You can pass in locale information as well.
```
Map<String, String> params = new HashMap<>();
params.put("lang", "fr");

yelpAPI.getBusiness('yelp-san-francisco', params);
```

### [Phone Search API](http://www.yelp.com/developers/documentation/v2/phone_search)
To query the Phone Search API, use the `getPhoneSearch` function with a phone number. Additional parameters can be
passed in within a `Map<String, String>` as specified in [Phone Search API Documentation](https://www.yelp.com/developers/documentation/v2/phone_search).
```
yelpAPI.getPhoneSearch("+15555555555");
```
You can pass in country code information as well
```
Map<String, String> params = new HashMap<>();
params.put("cc", "US");
params.put("category", "fashion");

yelpAPI.getPhoneSearch("5555555555", params);
```

## Responses
Responses from the API are parsed into Java objects.

Search and phone search responses are parsed into `SearchResponse` objects.

Business responses are parsed into `BusinessResponse` objects.

For a full list of available response fields, take a look at the documentation or the classes defined in [com.yelp
.clientlib.entities](https://github.com/Yelp/yelp-android/tree/add_readme/src/main/java/com/yelp/clientlib/entities)

## Contributing
1. Fork it ( http://github.com/yelp/yelp-android/fork )
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

//TODO: Add information about test suits.