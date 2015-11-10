package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class User {

    @Nullable
    public abstract String id();

    @Nullable
    public abstract String imageUrl();

    @Nullable
    public abstract String name();

    @JsonCreator
    public static User create(
            @JsonProperty("id") String id,
            @JsonProperty("image_url") String imageUrl,
            @JsonProperty("name") String name
    ) {
        return builder()
                .id(id)
                .imageUrl(imageUrl)
                .name(name)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder imageUrl(String imageUrl);

        public abstract Builder name(String name);

        public abstract User build();
    }

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }
}
