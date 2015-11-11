package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Location.Builder.class)
public abstract class Location {

    @Nullable
    public abstract ArrayList<String> address();

    @Nullable
    public abstract String city();

    @Nullable
    public abstract Coordinate coordinate();

    @Nullable
    public abstract String countryCode();

    @Nullable
    public abstract String crossStreets();

    @Nullable
    public abstract ArrayList<String> displayAddress();

    @Nullable
    public abstract Double geoAccuracy();

    @Nullable
    public abstract ArrayList<String> neighborhoods();

    @Nullable
    public abstract String postalCode();

    @Nullable
    public abstract String stateCode();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("address")
        public abstract Builder address(ArrayList<String> address);

        @JsonProperty("city")
        public abstract Builder city(String city);

        @JsonProperty("coordinate")
        public abstract Builder coordinate(Coordinate coordinate);

        @JsonProperty("country_code")
        public abstract Builder countryCode(String countryCode);

        @JsonProperty("cross_streets")
        public abstract Builder crossStreets(String crossStreets);

        @JsonProperty("display_address")
        public abstract Builder displayAddress(ArrayList<String> displayAddress);

        @JsonProperty("geo_accuracy")
        public abstract Builder geoAccuracy(Double geoAccuracy);

        @JsonProperty("neighborhoods")
        public abstract Builder neighborhoods(ArrayList<String> neighborhoods);

        @JsonProperty("postal_code")
        public abstract Builder postalCode(String postalCode);

        @JsonProperty("state_code")
        public abstract Builder stateCode(String stateCode);

        public abstract Location build();
    }

    public static Builder builder() {
        return new AutoValue_Location.Builder();
    }
}
