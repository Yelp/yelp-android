package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    public abstract static class Builder {

        @JsonProperty("excerpt")
        public abstract Builder excerpt(String excerpt);

        @JsonProperty("id")
        public abstract Builder id(String id);

        @JsonProperty("rating")
        public abstract Builder rating(Double rating);

        @JsonProperty("rating_image_url")
        public abstract Builder ratingImageUrl(String ratingImageUrl);

        @JsonProperty("rating_image_large_url")
        public abstract Builder ratingImageLargeUrl(String ratingImageLargeUrl);

        @JsonProperty("rating_image_small_url")
        public abstract Builder ratingImageSmallUrl(String ratingImageSmallUrl);

        @JsonProperty("time_created")
        public abstract Builder timeCreated(Long timeCreated);

        @JsonProperty("user")
        public abstract Builder user(User user);

        public abstract Review build();
    }

    public static Builder builder() {
        return new AutoValue_Review.Builder();
    }
}
