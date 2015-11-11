package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_GiftCertificateOption.Builder.class)
public abstract class GiftCertificateOption {

    @Nullable
    public abstract String formattedPrice();

    @Nullable
    public abstract Integer price();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("formatted_price")
        public abstract Builder formattedPrice(String formattedPrice);

        @JsonProperty("price")
        public abstract Builder price(Integer price);

        public abstract GiftCertificateOption build();
    }

    public static Builder builder() {
        return new AutoValue_GiftCertificateOption.Builder();
    }
}
