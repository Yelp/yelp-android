# yelp-android
A Java library for the Yelp API. 

## Installation
// TODO

## Usage

### Basics
This library uses an API object to query against the API. Make a API object by using `YelpAPIFactory` to create a
`YelpAPI` with you API keys.
```
YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
YelpAPI yelpAPI = apiFactory.createAPI();
```

### Search API
Once you have a yelpAPI you can use `search` to make a request to the Search API.
```
yelpAPI.search('San Francisco');
```
You can also pass in general params and locale options to the method as a Map<String, String>.
```
Map<String, String> params = new HashMap<>();
// General params
params.put("term", "food");
params.put("limit", "3");
// Locale params
params.put("lang", "fr");
yelpAPI.search('San Francisco', params);
```
The full list of parameters can be found on the Search API Documentation.

Additionally there are two more search methods for searching by a bounding box or for geographical coordinates:
```
BoundingBoxOptions bounds = BoundingBoxOptions.builder()
        .swLatitude(37.7577)
        .swLongitude(-122.4376)
        .neLatitude(37.785381)
        .neLongitude(-122.391681).build();

yelpAPI.search(bounds, params)

CoordinateOptions coordinate = CoordinateOptions.builder()
        .latitude(37.7867703362929)
        .longitude(-122.399958372115).build();

yelpAPI.search(coordinate, params)
```

### Business API
To query the Business API, use the `getBusiness` function with a business id. You can also pass in locale parameters in
 a `Map<String, String>` as specified in Business API Documentation.

### Phone Search API
To query the Phone Search API, use the `getPhoneSearch` function with a phone number. Additional parameters can be
passed in within a `Map<String, String>` as specified in Phone Search API Documentation.

## Responses
## Contributing
