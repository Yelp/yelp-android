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
@JsonDeserialize(builder = AutoValue_Business.Builder.class)
public abstract class Business implements Serializable {

    @Nullable
    public abstract ArrayList<Category> categories();

    @Nullable
    public abstract String displayPhone();

    @Nullable
    public abstract Double distance();

    @Nullable
    public abstract String eat24Url();

    @Nullable
    public abstract String id();

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
    public abstract String name();

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
    public abstract static class Builder {

        @JsonProperty("categories")
        public abstract Builder categories(ArrayList<Category> categories);

        @JsonProperty("display_phone")
        public abstract Builder displayPhone(String displayPhone);

        @JsonProperty("distance")
        public abstract Builder distance(Double distance);

        @JsonProperty("eat24_url")
        public abstract Builder eat24Url(String eat24Url);

        @JsonProperty("id")
        public abstract Builder id(String id);

        @JsonProperty("image_url")
        public abstract Builder imageUrl(String imageUrl);

        @JsonProperty("is_claimed")
        public abstract Builder isClaimed(Boolean isClaimed);

        @JsonProperty("is_closed")
        public abstract Builder isClosed(Boolean isClosed);

        @JsonProperty("menu_provider")
        public abstract Builder menuProvider(String menuProvider);

        @JsonProperty("menu_date_updated")
        public abstract Builder menuDateUpdated(Long menuDateUpdated);

        @JsonProperty("mobile_url")
        public abstract Builder mobileUrl(String mobileUrl);

        @JsonProperty("name")
        public abstract Builder name(String name);

        @JsonProperty("phone")
        public abstract Builder phone(String phone);

        @JsonProperty("reservation_url")
        public abstract Builder reservationUrl(String reservationUrl);

        @JsonProperty("review_count")
        public abstract Builder reviewCount(Integer reviewCount);

        @JsonProperty("snippet_image_url")
        public abstract Builder snippetImageUrl(String snippetImageUrl);

        @JsonProperty("snippet_text")
        public abstract Builder snippetText(String snippetText);

        @JsonProperty("url")
        public abstract Builder url(String url);

        @JsonProperty("deals")
        public abstract Builder deals(ArrayList<Deal> deals);

        @JsonProperty("gift_certificates")
        public abstract Builder giftCertificates(ArrayList<GiftCertificate> giftCertificates);

        @JsonProperty("location")
        public abstract Builder location(Location location);

        @JsonProperty("rating")
        public abstract Builder rating(Double ratingScore);

        @JsonProperty("rating_img_url")
        public abstract Builder ratingImgUrl(String ratingImgUrl);

        @JsonProperty("rating_img_url_large")
        public abstract Builder ratingImgUrlLarge(String ratingImgUrlLarge);

        @JsonProperty("rating_img_url_small")
        public abstract Builder ratingImgUrlSmall(String ratingImgUrlSmall);

        @JsonProperty("reviews")
        public abstract Builder reviews(ArrayList<Review> reviews);

        public abstract Business build();
    }

    public static Builder builder() {
        return new AutoValue_Business.Builder();
    }
}
