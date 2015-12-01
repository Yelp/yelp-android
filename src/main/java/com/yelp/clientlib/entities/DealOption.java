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
@JsonDeserialize(builder = AutoValue_DealOption.Builder.class)
public abstract class DealOption {

    @Nullable
    public abstract String formattedOriginalPrice();

    @Nullable
    public abstract String formattedPrice();

    @Nullable
    public abstract Boolean isQuantityLimited();

    @Nullable
    public abstract Integer originalPrice();

    @Nullable
    public abstract Integer price();

    @Nullable
    public abstract String purchaseUrl();

    @Nullable
    public abstract Integer remainingCount();

    @Nullable
    public abstract String title();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder formattedOriginalPrice(String formattedOriginalPrice);

        public abstract Builder formattedPrice(String formattedPrice);

        public abstract Builder isQuantityLimited(Boolean isQuantityLimited);

        public abstract Builder originalPrice(Integer originalPrice);

        public abstract Builder price(Integer price);

        public abstract Builder purchaseUrl(String purchaseUrl);

        public abstract Builder remainingCount(Integer remainingCount);

        public abstract Builder title(String title);

        public abstract DealOption build();
    }

    public static Builder builder() {
        return new AutoValue_DealOption.Builder();
    }
}
