package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Coordinate.Builder.class)
public abstract class Coordinate {

    public abstract Double latitude();

    public abstract Double longitude();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder latitude(Double latitude);

        public abstract Builder longitude(Double longitude);

        public abstract Coordinate build();
    }

    public static Builder builder() {
        return new AutoValue_Coordinate.Builder();
    }
}
