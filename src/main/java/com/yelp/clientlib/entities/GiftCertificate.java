package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_GiftCertificate.Builder.class)
public abstract class GiftCertificate implements Serializable {

    @Nullable
    public abstract String currencyCode();

    @Nullable
    public abstract String id();

    @Nullable
    public abstract String imageUrl();

    @Nullable
    public abstract String unusedBalances();

    @Nullable
    public abstract String url();

    @Nullable
    public abstract ArrayList<GiftCertificateOption> options();

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("currency_code")
        public abstract Builder currencyCode(String currencyCode);

        @JsonProperty("id")
        public abstract Builder id(String id);

        @JsonProperty("image_url")
        public abstract Builder imageUrl(String imageUrl);

        @JsonProperty("unused_balances")
        public abstract Builder unusedBalances(String unusedBalanced);

        @JsonProperty("url")
        public abstract Builder url(String url);

        @JsonProperty("options")
        public abstract Builder options(ArrayList<GiftCertificateOption> options);

        public abstract GiftCertificate build();
    }

    public static Builder builder() {
        return new AutoValue_GiftCertificate.Builder();
    }
}
