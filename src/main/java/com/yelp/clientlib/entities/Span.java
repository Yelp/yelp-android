package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Span {

    @Nullable
    public abstract Double latitudeDelta();

    @Nullable
    public abstract Double longitudeDelta();

    @JsonCreator
    public static Span create(
            @JsonProperty("latitude_delta") Double latitudeDelta,
            @JsonProperty("longitude_delta") Double longitudeDelta
    ) {
        return builder()
                .latitudeDelta(latitudeDelta)
                .longitudeDelta(longitudeDelta)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder latitudeDelta(Double latitudeDelta);

        public abstract Builder longitudeDelta(Double longitudeDelta);

        public abstract Span build();
    }

    public static Builder builder() {
        return new AutoValue_Span.Builder();
    }
}
