package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Deal.Builder.class)
public abstract class Deal implements Serializable {

    @Nullable
    public abstract String additionalRestrictions();

    @Nullable
    public abstract String currencyCode();

    @Nullable
    public abstract String id();

    @Nullable
    public abstract String imageUrl();

    @Nullable
    public abstract String importantRestriction();

    @Nullable
    public abstract Boolean isPopular();

    @Nullable
    public abstract ArrayList<DealOption> options();

    @Nullable
    public abstract Long timeEnd();

    @Nullable
    public abstract Long timeStart();

    @Nullable
    public abstract String title();

    @Nullable
    public abstract String url();

    @Nullable
    public abstract String whatYouGet();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {
        public abstract Builder additionalRestrictions(String additionalRestrictions);

        public abstract Builder currencyCode(String currencyCode);

        public abstract Builder id(String id);

        public abstract Builder imageUrl(String imageUrl);

        public abstract Builder importantRestriction(String importantRestriction);

        public abstract Builder isPopular(Boolean isPopular);

        public abstract Builder options(ArrayList<DealOption> options);

        public abstract Builder timeEnd(Long timeEnd);

        public abstract Builder timeStart(Long timeStart);

        public abstract Builder title(String title);

        public abstract Builder url(String url);

        public abstract Builder whatYouGet(String whatYouGet);

        public abstract Deal build();
    }

    public static Builder builder() {
        return new AutoValue_Deal.Builder();
    }
}
