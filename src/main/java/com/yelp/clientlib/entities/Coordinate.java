package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Coordinate {

    @Nullable
    public abstract Double latitude();

    @Nullable
    public abstract Double longitude();

    @JsonCreator
    public static Coordinate create(
            @JsonProperty("latitude") Double latitude,
            @JsonProperty("longitude") Double longitude
    ) {
        return builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder latitude(Double latitude);

        public abstract Builder longitude(Double longitude);

        public abstract Coordinate build();
    }

    public static Builder builder() {
        return new AutoValue_Coordinate.Builder();
    }
}
