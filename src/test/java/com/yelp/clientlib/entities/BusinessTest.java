package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BusinessTest extends EntityTest {
    public BusinessTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode businessNode = this.businessResponseJsonNode;
        Business business = this.objectMapper.readValue(businessNode.toString(), Business.class);

        Assert.assertNotNull(business.categories());
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
        Assert.assertEquals(new Integer(businessNode.path("review_count").asInt()), business.reviewCount());
        Assert.assertEquals(businessNode.path("snippet_image_url").textValue(), business.snippetImageUrl());
        Assert.assertEquals(businessNode.path("snippet_text").textValue(), business.snippetText());
        Assert.assertEquals(businessNode.path("url").textValue(), business.url());

        Assert.assertNotNull(business.deals());
        Assert.assertNotNull(business.giftCertificates());
        Assert.assertNotNull(business.location());
        Assert.assertNotNull(business.rating());
        Assert.assertNotNull(business.reviews());
    }
}
