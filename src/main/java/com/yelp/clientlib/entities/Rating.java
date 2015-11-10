package com.yelp.clientlib.entities;

import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
public abstract class Rating {

    @Nullable
    public abstract Double rating();

    @Nullable
    public abstract String imgUrl();

    @Nullable
    public abstract String imgUrlLarge();

    @Nullable
    public abstract String imgUrlSmall();

    public static Rating create(
            Double rating,
            String imgUrl,
            String imgUrlLarge,
            String imgUrlSmall

    ) {
        return builder()
                .rating(rating)
                .imgUrl(imgUrl)
                .imgUrlLarge(imgUrlLarge)
                .imgUrlSmall(imgUrlSmall)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder rating(Double rating);

        public abstract Builder imgUrl(String imgUrl);

        public abstract Builder imgUrlLarge(String imgUrlLarge);

        public abstract Builder imgUrlSmall(String imgUrlSmall);

        public abstract Rating build();
    }

    public static Builder builder() {
        return new AutoValue_Rating.Builder();
    }
}
