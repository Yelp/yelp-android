package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GiftCertificateOption {

    @Nullable
    public abstract String formattedPrice();

    @Nullable
    public abstract Integer price();

    @JsonCreator
    public static GiftCertificateOption create(
            @JsonProperty("formatted_price") String formattedPrice,
            @JsonProperty("price") Integer price

    ) {
        return builder()
                .formattedPrice(formattedPrice)
                .price(price)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder formattedPrice(String formattedPrice);

        public abstract Builder price(Integer price);

        public abstract GiftCertificateOption build();
    }

    public static Builder builder() {
        return new AutoValue_GiftCertificateOption.Builder();
    }
}
