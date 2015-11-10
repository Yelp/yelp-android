package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Region {

    @Nullable
    public abstract Coordinate center();

    @Nullable
    public abstract Span span();

    @JsonCreator
    public static Region create(
            @JsonProperty("center") Coordinate center,
            @JsonProperty("span") Span span
    ) {
        return builder()
                .center(center)
                .span(span)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder center(Coordinate center);

        public abstract Builder span(Span span);

        public abstract Region build();
    }

    public static Builder builder() {
        return new AutoValue_Region.Builder();
    }
}
