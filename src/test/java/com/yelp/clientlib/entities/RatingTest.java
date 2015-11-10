package com.yelp.clientlib.entities;


import org.junit.Assert;
import org.junit.Test;

public class RatingTest {

    @Test
    public void testCreate() {
        Double testRating = 4.5;
        String testImgUrl = "https://yelp.com/image/test.png";
        String testImgUrlLarge = "https://yelp.com/image/test_large.png";
        String testImgUrlSmall = "https://yelp.com/image/test_small.png";

        Rating rating = Rating.create(testRating, testImgUrl, testImgUrlLarge, testImgUrlSmall);

        Assert.assertEquals(testRating, rating.rating());
        Assert.assertEquals(testImgUrl, rating.imgUrl());
        Assert.assertEquals(testImgUrlLarge, rating.imgUrlLarge());
        Assert.assertEquals(testImgUrlSmall, rating.imgUrlSmall());
    }
}
