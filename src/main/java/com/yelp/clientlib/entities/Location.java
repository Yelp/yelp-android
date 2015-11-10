package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonCreator
    public static Location create(
            @JsonProperty("address") ArrayList<String> address,
            @JsonProperty("city") String city,
            @JsonProperty("coordinate") Coordinate coordinate,
            @JsonProperty("country_code") String countryCode,
            @JsonProperty("cross_streets") String crossStreets,
            @JsonProperty("display_address") ArrayList<String> displayAddress,
            @JsonProperty("geo_accuracy") Double geoAccuracy,
            @JsonProperty("neighborhoods") ArrayList<String> neighborhoods,
            @JsonProperty("postal_code") String postalCode,
            @JsonProperty("state_code") String stateCode
    ) {
        return builder()
                .address(address)
                .city(city)
                .coordinate(coordinate)
                .countryCode(countryCode)
                .crossStreets(crossStreets)
                .displayAddress(displayAddress)
                .geoAccuracy(geoAccuracy)
                .neighborhoods(neighborhoods)
                .postalCode(postalCode)
                .stateCode(stateCode)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder address(ArrayList<String> address);

        public abstract Builder city(String city);

        public abstract Builder coordinate(Coordinate coordinate);

        public abstract Builder countryCode(String countryCode);

        public abstract Builder crossStreets(String crossStreets);

        public abstract Builder displayAddress(ArrayList<String> displayAddress);

        public abstract Builder geoAccuracy(Double geoAccuracy);

        public abstract Builder neighborhoods(ArrayList<String> neighborhoods);

        public abstract Builder postalCode(String postalCode);

        public abstract Builder stateCode(String stateCode);

        public abstract Location build();
    }

    public static Builder builder() {
        return new AutoValue_Location.Builder();
    }
}
