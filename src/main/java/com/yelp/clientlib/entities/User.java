package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_User.Builder.class)
public abstract class User {

    @Nullable
    public abstract String id();

    @Nullable
    public abstract String imageUrl();

    @Nullable
    public abstract String name();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("id")
        public abstract Builder id(String id);

        @JsonProperty("image_url")
        public abstract Builder imageUrl(String imageUrl);

        @JsonProperty("name")
        public abstract Builder name(String name);

        public abstract User build();
    }

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }
}
