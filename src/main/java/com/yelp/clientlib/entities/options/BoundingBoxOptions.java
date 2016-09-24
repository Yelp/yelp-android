package com.yelp.clientlib.entities.options;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class BoundingBoxOptions {

    /**
     * @return Southwest latitude of bounding box.
     */
    public abstract Double swLatitude();

    /**
     * @return Southwest longitude of bounding box.
     */
    public abstract Double swLongitude();

    /**
     * @return Northeast latitude of bounding box.
     */
    public abstract Double neLatitude();

    /**
     * @return Northeast longitude of bounding box.
     */
    public abstract Double neLongitude();

    /**
     * String presentation for {@link BoundingBoxOptions}. The generated string is encoded as
     * "swLatitude,swLongitude%7CneLatitude,neLongitude". This method is used by {@link retrofit2.http.Query} to
     * generate the values of query parameters.
     *
     * BoundingBox query param value contains non-suggested URI character '|' which doesn't fit into most of the
     * signature functions, we encode it here into "%7C" so it's not passed through http client.
     *
     * @return String presentation for {@link BoundingBoxOptions}
     * @see <a href=https://www.yelp.com/developers/documentation/v2/search_api#searchGBB>https://www.yelp.com/developers/documentation/v2/search_api#searchGBB</a>
     */
    @Override
    public String toString() {
        return String.format("%f,%f%%7C%f,%f", swLatitude(), swLongitude(), neLatitude(), neLongitude());
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * @param latitude Sets southwest latitude.
         *
         * @return this
         */
        public abstract Builder swLatitude(Double latitude);

        /**
         * @param longitude Sets southwest longitude.
         *
         * @return this
         */
        public abstract Builder swLongitude(Double longitude);

        /**
         * @param latitude Sets northeast latitude.
         *
         * @return this
         */
        public abstract Builder neLatitude(Double latitude);

        /**
         * @param longitude Sets northeast longitude.
         *
         * @return this
         */
        public abstract Builder neLongitude(Double longitude);

        /**
         * Returns a reference to the object of {@link BoundingBoxOptions} being constructed by the builder.
         *
         * @return the {@link BoundingBoxOptions} constructed by the builder.
         */
        public abstract BoundingBoxOptions build();
    }

    public static Builder builder() {
        return new AutoValue_BoundingBoxOptions.Builder();
    }
}
