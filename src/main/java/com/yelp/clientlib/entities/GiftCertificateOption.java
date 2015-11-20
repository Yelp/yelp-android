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
@JsonDeserialize(builder = AutoValue_GiftCertificateOption.Builder.class)
public abstract class GiftCertificateOption {

    @Nullable
    public abstract String formattedPrice();

    @Nullable
    public abstract Integer price();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder formattedPrice(String formattedPrice);

        public abstract Builder price(Integer price);

        public abstract GiftCertificateOption build();
    }

    public static Builder builder() {
        return new AutoValue_GiftCertificateOption.Builder();
    }
}
