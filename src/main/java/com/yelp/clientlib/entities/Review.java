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
@JsonDeserialize(builder = AutoValue_Review.Builder.class)
public abstract class Review {

    @Nullable
    public abstract String excerpt();

    @Nullable
    public abstract String id();

    @Nullable
    public abstract Double rating();

    @Nullable
    public abstract String ratingImageUrl();

    @Nullable
    public abstract String ratingImageLargeUrl();

    @Nullable
    public abstract String ratingImageSmallUrl();

    @Nullable
    public abstract Long timeCreated();

    @Nullable
    public abstract User user();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder excerpt(String excerpt);

        public abstract Builder id(String id);

        public abstract Builder rating(Double rating);

        public abstract Builder ratingImageUrl(String ratingImageUrl);

        public abstract Builder ratingImageLargeUrl(String ratingImageLargeUrl);

        public abstract Builder ratingImageSmallUrl(String ratingImageSmallUrl);

        public abstract Builder timeCreated(Long timeCreated);

        public abstract Builder user(User user);

        public abstract Review build();
    }

    public static Builder builder() {
        return new AutoValue_Review.Builder();
    }
}
