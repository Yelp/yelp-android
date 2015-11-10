package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonCreator
    public static DealOption create(
            @JsonProperty("formatted_original_price") String formattedOriginalPrice,
            @JsonProperty("formatted_price") String formattedPrice,
            @JsonProperty("is_quantity_limited") Boolean isQuantityLimited,
            @JsonProperty("original_price") Integer originalPrice,
            @JsonProperty("price") Integer price,
            @JsonProperty("purchase_url") String purchaseUrl,
            @JsonProperty("remaining_count") Integer remainingCount,
            @JsonProperty("title") String title
    ) {
        return builder()
                .formattedOriginalPrice(formattedOriginalPrice)
                .formattedPrice(formattedPrice)
                .isQuantityLimited(isQuantityLimited)
                .originalPrice(originalPrice)
                .price(price)
                .purchaseUrl(purchaseUrl)
                .remainingCount(remainingCount)
                .title(title)
                .build();
    }

    @AutoValue.Builder
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
