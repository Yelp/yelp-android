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
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
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
