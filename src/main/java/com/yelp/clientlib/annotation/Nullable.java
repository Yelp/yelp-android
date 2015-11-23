package com.yelp.clientlib.annotation;

/**
 * Indicate whether the attribute in an object could be deserialized or set into null.
 *
 * For attribute has no {@link Nullable} decorator, {@link com.fasterxml.jackson.databind.JsonMappingException} is
 * raised while performing deserialization if the attribute is missing in the JSON string;
 * {@link java.lang.IllegalStateException} is raised while using builder to construct an instance with the attribute
 * set as null.
 */

public @interface Nullable {

}
