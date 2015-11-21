package com.yelp.clientlib.entities;


import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ReviewTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode reviewNode = JsonTestUtils.getBusinessResponseJsonNode().path("reviews").get(0);
        Review review = JsonTestUtils.deserializeJson(reviewNode.toString(), Review.class);

        Assert.assertEquals(reviewNode.path("excerpt").textValue(), review.excerpt());
        Assert.assertEquals(reviewNode.path("id").textValue(), review.id());
        Assert.assertEquals(new Double(reviewNode.path("rating").asDouble()), review.rating());
        Assert.assertEquals(reviewNode.path("rating_image_large_url").textValue(), review.ratingImageLargeUrl());
        Assert.assertEquals(reviewNode.path("rating_image_small_url").textValue(), review.ratingImageSmallUrl());
        Assert.assertEquals(reviewNode.path("rating_image_url").textValue(), review.ratingImageUrl());
        Assert.assertEquals(new Long(reviewNode.path("time_created").asLong()), review.timeCreated());

        // User is tested in it's own tests.
        Assert.assertNotNull(review.user());
    }
}
