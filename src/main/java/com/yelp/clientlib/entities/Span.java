package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder latitudeDelta(Double latitudeDelta);

        public abstract Builder longitudeDelta(Double longitudeDelta);

        public abstract Span build();
    }

    public static Builder builder() {
        return new AutoValue_Span.Builder();
    }
}
