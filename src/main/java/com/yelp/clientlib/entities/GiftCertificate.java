package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonCreator
    public static GiftCertificate create(
            @JsonProperty("currency_code") String currencyCode,
            @JsonProperty("id") String id,
            @JsonProperty("image_url") String imageUrl,
            @JsonProperty("unused_balances") String unusedBalances,
            @JsonProperty("url") String url,
            @JsonProperty("options") ArrayList<GiftCertificateOption> options
    ) {
        return builder()
                .currencyCode(currencyCode)
                .id(id)
                .imageUrl(imageUrl)
                .unusedBalances(unusedBalances)
                .url(url)
                .options(options)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder currencyCode(String currencyCode);

        public abstract Builder id(String id);

        public abstract Builder imageUrl(String imageUrl);

        public abstract Builder unusedBalances(String unusedBalanced);

        public abstract Builder url(String url);

        public abstract Builder options(ArrayList<GiftCertificateOption> options);

        public abstract GiftCertificate build();
    }

    public static Builder builder() {
        return new AutoValue_GiftCertificate.Builder();
    }
}
