package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Coordinate.Builder.class)
public abstract class Coordinate {

    @Nullable
    public abstract Double latitude();

    @Nullable
    public abstract Double longitude();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("latitude")
        public abstract Builder latitude(Double latitude);

        @JsonProperty("longitude")
        public abstract Builder longitude(Double longitude);

        public abstract Coordinate build();
    }

    public static Builder builder() {
        return new AutoValue_Coordinate.Builder();
    }
}
