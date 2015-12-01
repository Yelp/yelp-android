package com.yelp.clientlib.annotation;

/**
 * Indicates whether a field can be null.
 *
 * For fields with no {@link Nullable} decoration, {@link com.fasterxml.jackson.databind.JsonMappingException} is
 * raised while performing deserialization if the field is missing in the JSON string.
 * {@link java.lang.IllegalStateException} is raised while using builder to construct an instance with the field set as
 * null.
 */

public @interface Nullable {

}
