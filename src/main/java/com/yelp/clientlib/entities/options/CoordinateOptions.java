package com.yelp.clientlib.entities.options;

import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
public abstract class CoordinateOptions {

    /**
     * @return Latitude of geo-point to search near.
     */
    public abstract Double latitude();

    /**
     * @return Longitude of geo-point to search near.
     */
    public abstract Double longitude();

    /**
     * @return Optional accuracy of latitude, longitude.
     */
    @Nullable
    public abstract Double accuracy();

    /**
     * @return Optional altitude of geo-point to search near.
     */
    @Nullable
    public abstract Double altitude();

    /**
     * @return Optional accuracy of altitude.
     */
    @Nullable
    public abstract Double altitudeAccuracy();

    /**
     * String presentation for {@code CoordinateOptions}. The generated string is comma separated. It is encoded in the
     * order of latitude, longitude, accuracy, altitude and altitudeAccuracy. This method is used by {@code retrofit
     * .http.QueryMap} to generate the values of query parameters.
     *
     * @return String presentation for {@code CoordinateOptions}
     */
    @Override
    public String toString() {
        Double[] optionalFields = new Double[]{accuracy(), altitude(), altitudeAccuracy()};

        String coordinate = latitude() + "," + longitude();
        for (Double field : optionalFields) {
            coordinate = String.format("%s,%s", coordinate, (field == null) ? "" : field.toString());
        }

        return coordinate;
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * @param latitude Sets latitude.
         *
         * @return this
         */
        public abstract Builder latitude(Double latitude);

        /**
         * @param longitude Sets longitude.
         *
         * @return this
         */
        public abstract Builder longitude(Double longitude);

        /**
         * @param accuracy Sets accuracy of latitude, longitude.
         *
         * @return this
         */
        public abstract Builder accuracy(Double accuracy);

        /**
         * @param altitude Sets altitude.
         *
         * @return this
         */
        public abstract Builder altitude(Double altitude);

        /**
         * @param altitudeAccuracy Sets accuracy of altitude.
         *
         * @return this
         */
        public abstract Builder altitudeAccuracy(Double altitudeAccuracy);

        /**
         * Returns a reference to the object of {@code CoordinateOptions} being constructed by the builder.
         *
         * @return the {@code CoordinateOptions} constructed by the builder.
         */
        public abstract CoordinateOptions build();
    }

    public static Builder builder() {
        return new AutoValue_CoordinateOptions.Builder();
    }
}
