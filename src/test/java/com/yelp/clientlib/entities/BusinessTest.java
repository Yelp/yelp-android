package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BusinessTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode businessNode = JsonTestUtils.getBusinessResponseJsonNode();
        Business business = JsonTestUtils.deserializeJson(businessNode.toString(), Business.class);

        Assert.assertEquals(businessNode.path("display_phone").textValue(), business.displayPhone());
        Assert.assertEquals(businessNode.path("eat24_url").textValue(), business.eat24Url());
        Assert.assertEquals(businessNode.path("id").textValue(), business.id());
        Assert.assertEquals(businessNode.path("image_url").textValue(), business.imageUrl());
        Assert.assertEquals(businessNode.path("is_claimed").asBoolean(), business.isClaimed());
        Assert.assertEquals(businessNode.path("is_closed").asBoolean(), business.isClosed());
        Assert.assertEquals(new Long(businessNode.path("menu_date_updated").asLong()), business.menuDateUpdated());
        Assert.assertEquals(businessNode.path("menu_provider").textValue(), business.menuProvider());
        Assert.assertEquals(businessNode.path("mobile_url").textValue(), business.mobileUrl());
        Assert.assertEquals(businessNode.path("name").textValue(), business.name());
        Assert.assertEquals(businessNode.path("phone").textValue(), business.phone());
        Assert.assertEquals(businessNode.path("reservation_url").textValue(), business.reservationUrl());
        Assert.assertEquals(new Double(businessNode.path("rating").asDouble()), business.rating());
        Assert.assertEquals(businessNode.path("rating_img_url").textValue(), business.ratingImgUrl());
        Assert.assertEquals(businessNode.path("rating_img_url_large").textValue(), business.ratingImgUrlLarge());
        Assert.assertEquals(businessNode.path("rating_img_url_small").textValue(), business.ratingImgUrlSmall());
        Assert.assertEquals(new Integer(businessNode.path("review_count").asInt()), business.reviewCount());
        Assert.assertEquals(businessNode.path("snippet_image_url").textValue(), business.snippetImageUrl());
        Assert.assertEquals(businessNode.path("snippet_text").textValue(), business.snippetText());
        Assert.assertEquals(businessNode.path("url").textValue(), business.url());

        // The following objects are tested in their own tests.
        Assert.assertNotNull(business.categories());
        Assert.assertNotNull(business.deals());
        Assert.assertNotNull(business.giftCertificates());
        Assert.assertNotNull(business.location());
        Assert.assertNotNull(business.reviews());

    }

    @Test
    public void testDeserializationWithMissingNullableAttribute() throws IOException {
        String businessJsonString = "{\"name\":\"Yelp\", \"id\":\"yelp-san-francisco\"}";
        Business business = JsonTestUtils.deserializeJson(businessJsonString, Business.class);
        Assert.assertEquals("Yelp", business.name());
        Assert.assertEquals("yelp-san-francisco", business.id());
        Assert.assertNull(business.displayPhone());
    }

    @Test
    public void testDeserializationWithUTF8Characters() throws IOException {
        String businessJsonString = "{\"name\":\"Gööd Füsiön Fööd\", \"id\":\"gööd-füsiön-fööd-san-francisco\"}";
        Business business = JsonTestUtils.deserializeJson(businessJsonString, Business.class);
        Assert.assertEquals("Gööd Füsiön Fööd", business.name());
        Assert.assertEquals("gööd-füsiön-fööd-san-francisco", business.id());
    }

    @Test
    public void testDeserializationWithNoReviewBusinessHasNullForReview() throws IOException {
        JsonNode businessNode = JsonTestUtils.getJsonNodeFromFile("noReviewBusinessResponse.json");
        Business business = JsonTestUtils.deserializeJson(businessNode.toString(), Business.class);
        Assert.assertNull(business.reviews());
        Assert.assertEquals(new Integer(0), business.reviewCount());
        Assert.assertEquals(businessNode.path("id").textValue(), business.id());
        Assert.assertEquals(businessNode.path("rating_img_url").textValue(), business.ratingImgUrl());
        Assert.assertEquals(businessNode.path("rating_img_url_small").textValue(), business.ratingImgUrlSmall());
    }

    @Test(expected = JsonMappingException.class)
    public void testDeserializationFailedWithMissingAttributes() throws IOException {
        String businessJsonString = "{\"name\":\"Yelp\"}";
        JsonTestUtils.deserializeJson(businessJsonString, Business.class);
    }

    @Test
    public void testBuildWithNullableAttributesNotSet() throws IOException {
        Business.builder().name("Yelp").id("yelp-san-francisco").build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithMissingId() throws IOException {
        Business.builder().name("Yelp").build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithMissingName() throws IOException {
        Business.builder().id("yelp-san-francisco").build();
    }
}
