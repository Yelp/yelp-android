package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Region.Builder.class)
public abstract class Region {

    @Nullable
    public abstract Coordinate center();

    @Nullable
    public abstract Span span();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("center")
        public abstract Builder center(Coordinate center);

        @JsonProperty("span")
        public abstract Builder span(Span span);

        public abstract Region build();
    }

    public static Builder builder() {
        return new AutoValue_Region.Builder();
    }
}
