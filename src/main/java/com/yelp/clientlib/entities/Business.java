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
public abstract class Business implements Serializable {

    @Nullable
    public abstract ArrayList<ArrayList<String>> categories();

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
    public abstract Rating rating();

    @Nullable
    public abstract ArrayList<Review> reviews();

    @JsonCreator
    public static Business create(
            @JsonProperty("categories") ArrayList<ArrayList<String>> categories,
            @JsonProperty("display_phone") String displayPhone,
            @JsonProperty("distance") Double distance,
            @JsonProperty("eat24_url") String eat24Url,
            @JsonProperty("id") String id,
            @JsonProperty("image_url") String imageUrl,
            @JsonProperty("is_claimed") Boolean isClaimed,
            @JsonProperty("is_closed") Boolean isClosed,
            @JsonProperty("menu_provider") String menuProvider,
            @JsonProperty("menu_date_updated") Long menuDateUpdated,
            @JsonProperty("mobile_url") String mobileUrl,
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("reservation_url") String reservationUrl,
            @JsonProperty("review_count") Integer reviewCount,
            @JsonProperty("snippet_image_url") String snippetImageUrl,
            @JsonProperty("snippet_text") String snippetText,
            @JsonProperty("url") String url,
            @JsonProperty("deals") ArrayList<Deal> deals,
            @JsonProperty("gift_certificates") ArrayList<GiftCertificate> giftCertificates,
            @JsonProperty("location") Location location,
            @JsonProperty("rating") Double rating,
            @JsonProperty("rating_img_url") String ratingImgUrl,
            @JsonProperty("rating_img_url_large") String ratingImgUrlLarge,
            @JsonProperty("rating_img_url_small") String ratingImgUrlSmall,
            @JsonProperty("reviews") ArrayList<Review> reviews
    ) {
        return builder()
                .categories(categories)
                .displayPhone(displayPhone)
                .distance(distance)
                .eat24Url(eat24Url)
                .id(id)
                .imageUrl(imageUrl)
                .isClaimed(isClaimed)
                .isClosed(isClosed)
                .menuProvider(menuProvider)
                .menuDateUpdated(menuDateUpdated)
                .mobileUrl(mobileUrl)
                .name(name)
                .phone(phone)
                .reservationUrl(reservationUrl)
                .reviewCount(reviewCount)
                .snippetImageUrl(snippetImageUrl)
                .snippetText(snippetText)
                .url(url)
                .deals(deals)
                .giftCertificates(giftCertificates)
                .location(location)
                .rating(Rating.create(rating, ratingImgUrl, ratingImgUrlLarge, ratingImgUrlSmall))
                .reviews(reviews)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder categories(ArrayList<ArrayList<String>> categories);

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

        public abstract Builder rating(Rating rating);

        public abstract Builder reviews(ArrayList<Review> reviews);

        public abstract Business build();
    }

    public static Builder builder() {
        return new AutoValue_Business.Builder();
    }
}
