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
@JsonDeserialize(builder = AutoValue_Business.Builder.class)
public abstract class Business implements Serializable {

    public abstract String id();

    public abstract String name();

    @Nullable
    public abstract ArrayList<Category> categories();

    @Nullable
    public abstract String displayPhone();

    @Nullable
    public abstract Double distance();

    @Nullable
    public abstract String eat24Url();

    @Nullable
    public abstract String imageUrl();

    @Nullable
    public abstract Boolean isClaimed();

    @Nullable
    public abstract Boolean isClosed();

    @Nullable
    public abstract String menuProvider();

    @Nullable
    public abstract Long menuDateUpdated();

    @Nullable
    public abstract String mobileUrl();

    @Nullable
    public abstract String phone();

    @Nullable
    public abstract String reservationUrl();

    @Nullable
    public abstract Integer reviewCount();

    @Nullable
    public abstract String snippetImageUrl();

    @Nullable
    public abstract String snippetText();

    @Nullable
    public abstract String url();

    @Nullable
    public abstract ArrayList<Deal> deals();

    @Nullable
    public abstract ArrayList<GiftCertificate> giftCertificates();

    @Nullable
    public abstract Location location();

    @Nullable
    public abstract Double rating();

    @Nullable
    public abstract String ratingImgUrl();

    @Nullable
    public abstract String ratingImgUrlLarge();

    @Nullable
    public abstract String ratingImgUrlSmall();

    @Nullable
    public abstract ArrayList<Review> reviews();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder categories(ArrayList<Category> categories);

        public abstract Builder displayPhone(String displayPhone);

        public abstract Builder distance(Double distance);

        public abstract Builder eat24Url(String eat24Url);

        public abstract Builder id(String id);

        public abstract Builder imageUrl(String imageUrl);

        public abstract Builder isClaimed(Boolean isClaimed);

        public abstract Builder isClosed(Boolean isClosed);

        public abstract Builder menuProvider(String menuProvider);

        public abstract Builder menuDateUpdated(Long menuDateUpdated);

        public abstract Builder mobileUrl(String mobileUrl);

        public abstract Builder name(String name);

        public abstract Builder phone(String phone);

        public abstract Builder reservationUrl(String reservationUrl);

        public abstract Builder reviewCount(Integer reviewCount);

        public abstract Builder snippetImageUrl(String snippetImageUrl);

        public abstract Builder snippetText(String snippetText);

        public abstract Builder url(String url);

        public abstract Builder deals(ArrayList<Deal> deals);

        public abstract Builder giftCertificates(ArrayList<GiftCertificate> giftCertificates);

        public abstract Builder location(Location location);

        public abstract Builder rating(Double ratingScore);

        public abstract Builder ratingImgUrl(String ratingImgUrl);

        public abstract Builder ratingImgUrlLarge(String ratingImgUrlLarge);

        public abstract Builder ratingImgUrlSmall(String ratingImgUrlSmall);

        public abstract Builder reviews(ArrayList<Review> reviews);

        public abstract Business build();
    }

    public static Builder builder() {
        return new AutoValue_Business.Builder();
    }
}
