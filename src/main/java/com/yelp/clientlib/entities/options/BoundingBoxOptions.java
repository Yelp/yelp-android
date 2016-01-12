package com.yelp.clientlib.entities.options;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class BoundingBoxOptions {

    /**
     * Southwest latitude of bounding box.
     */
    public abstract Double swLatitude();

    /**
     * Southwest longitude of bounding box.
     */
    public abstract Double swLongitude();

    /**
     * Northeast latitude of bounding box.
     */
    public abstract Double neLatitude();

    /**
     * Northeast longitude of bounding box.
     */
    public abstract Double neLongitude();

    /**
     * String presentation for {@link BoundingBoxOptions}. The generated string is formatted as "swLatitude,
     * swLongitude|neLatitude,neLongitude". This method is used by {@link retrofit.http.QueryMap} to generate the
     * values of query parameters.
     *
     * @return String presentation for {@link BoundingBoxOptions}
     */
    @Override
    public String toString() {
        return String.format("%f,%f|%f,%f", swLatitude(), swLongitude(), neLatitude(), neLongitude());
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * Sets southwest latitude.
         *
         * @return this
         */
        public abstract Builder swLatitude(Double latitude);

        /**
         * Sets southwest longitude.
         *
         * @return this
         */
        public abstract Builder swLongitude(Double longitude);

        /**
         * Sets northeast latitude.
         *
         * @return this
         */
        public abstract Builder neLatitude(Double latitude);

        /**
         * Sets northeast longitude.
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
