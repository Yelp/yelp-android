package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Review {

    @Nullable
    public abstract String excerpt();

    @Nullable
    public abstract String id();

    @Nullable
    public abstract Rating rating();

    @Nullable
    public abstract Long timeCreated();

    @Nullable
    public abstract User user();

    @JsonCreator
    public static Review create(
            @JsonProperty("excerpt") String excerpt,
            @JsonProperty("id") String id,
            @JsonProperty("rating") Double rating,
            @JsonProperty("rating_image_url") String ratingImageUrl,
            @JsonProperty("rating_image_large_url") String ratingImageLargeUrl,
            @JsonProperty("rating_image_small_url") String ratingImageSmallUrl,
            @JsonProperty("time_created") Long timeCreated,
            @JsonProperty("user") User user
    ) {
        return builder()
                .excerpt(excerpt)
                .id(id)
                .rating(Rating.create(rating, ratingImageUrl, ratingImageLargeUrl, ratingImageSmallUrl))
                .timeCreated(timeCreated)
                .user(user)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder excerpt(String excerpt);

        public abstract Builder id(String id);

        public abstract Builder rating(Rating rating);

        public abstract Builder timeCreated(Long timeCreated);

        public abstract Builder user(User user);

        public abstract Review build();
    }

    public static Builder builder() {
        return new AutoValue_Review.Builder();
    }
}
