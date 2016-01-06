package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DealTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode dealNode = JsonTestUtils.getBusinessResponseJsonNode().path("deals").get(0);
        Deal deal = JsonTestUtils.deserializeJson(dealNode.toString(), Deal.class);

        Assert.assertNull(deal.additionalRestrictions());
        Assert.assertEquals(dealNode.path("currency_code").textValue(), deal.currencyCode());
        Assert.assertNull(deal.id());
        Assert.assertEquals(dealNode.path("image_url").textValue(), deal.imageUrl());
        Assert.assertNull(deal.importantRestrictions());
        Assert.assertEquals(dealNode.path("is_popular").asBoolean(), deal.isPopular());
        Assert.assertNotNull(deal.options().get(0));
        Assert.assertNull(deal.timeEnd());
        Assert.assertEquals(new Long(dealNode.path("time_start").asLong()), deal.timeStart());
        Assert.assertEquals(dealNode.path("title").textValue(), deal.title());
        Assert.assertEquals(dealNode.path("url").textValue(), deal.url());
        Assert.assertNull(deal.whatYouGet());
    }
}
