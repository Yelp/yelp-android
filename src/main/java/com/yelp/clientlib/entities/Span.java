package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Span.Builder.class)
public abstract class Span {

    @Nullable
    public abstract Double latitudeDelta();

    @Nullable
    public abstract Double longitudeDelta();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("latitude_delta")
        public abstract Builder latitudeDelta(Double latitudeDelta);

        @JsonProperty("longitude_delta")
        public abstract Builder longitudeDelta(Double longitudeDelta);

        public abstract Span build();
    }

    public static Builder builder() {
        return new AutoValue_Span.Builder();
    }
}
