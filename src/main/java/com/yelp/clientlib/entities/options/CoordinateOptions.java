package com.yelp.clientlib.entities.options;

import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
public abstract class CoordinateOptions {

    /**
     * Latitude of geo-point to search near.
     */
    public abstract Double latitude();

    /**
     * Longitude of geo-point to search near.
     */
    public abstract Double longitude();

    /**
     * Optional accuracy of latitude, longitude.
     */
    @Nullable
    public abstract Double accuracy();

    /**
     * Optional altitude of geo-point to search near.
     */
    @Nullable
    public abstract Double altitude();

    /**
     * Optional accuracy of altitude.
     */
    @Nullable
    public abstract Double altitudeAccuracy();

    /**
     * String presentation for {@link CoordinateOptions}. The generated string is comma separated. It is encoded in the
     * order of latitude, longitude, accuracy, altitude and altitudeAccuracy. This method is used by {@link retrofit
     * .http.QueryMap} to generate the values of query parameters.
     *
     * @return String presentation for {@link CoordinateOptions}
     */
    @Override
    public String toString() {
        Double[] fields = new Double[]{accuracy(), altitude(), altitudeAccuracy()};

        String coordinate = latitude() + "," + longitude();
        for (Double field : fields) {
            coordinate = String.format("%s,%s", coordinate, (field == null) ? "" : field.toString());
        }

        return coordinate;
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * Sets latitude.
         *
         * @return this
         */
        public abstract Builder latitude(Double latitude);

        /**
         * Sets longitude.
         *
         * @return this
         */
        public abstract Builder longitude(Double longitude);

        /**
         * Sets accuracy of latitude, longitude.
         *
         * @return this
         */
        public abstract Builder accuracy(Double latitude);

        /**
         * Sets altitude.
         *
         * @return this
         */
        public abstract Builder altitude(Double altitude);

        /**
         * Sets accuracy of altitude.
         *
         * @return this
         */
        public abstract Builder altitudeAccuracy(Double altitudeAccuracy);

        /**
         * Returns a reference to the object of {@link CoordinateOptions} being constructed by the builder.
         *
         * @return the {@link CoordinateOptions} constructed by the builder.
         */
        public abstract CoordinateOptions build();
    }

    public static Builder builder() {
        return new AutoValue_CoordinateOptions.Builder();
    }
}
