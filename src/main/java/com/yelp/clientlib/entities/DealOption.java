package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    public abstract static class Builder {

        @JsonProperty("formatted_original_price")
        public abstract Builder formattedOriginalPrice(String formattedOriginalPrice);

        @JsonProperty("formatted_price")
        public abstract Builder formattedPrice(String formattedPrice);

        @JsonProperty("is_quantity_limited")
        public abstract Builder isQuantityLimited(Boolean isQuantityLimited);

        @JsonProperty("original_price")
        public abstract Builder originalPrice(Integer originalPrice);

        @JsonProperty("price")
        public abstract Builder price(Integer price);

        @JsonProperty("purchase_url")
        public abstract Builder purchaseUrl(String purchaseUrl);

        @JsonProperty("remaining_count")
        public abstract Builder remainingCount(Integer remainingCount);

        @JsonProperty("title")
        public abstract Builder title(String title);

        public abstract DealOption build();
    }

    public static Builder builder() {
        return new AutoValue_DealOption.Builder();
    }
}
