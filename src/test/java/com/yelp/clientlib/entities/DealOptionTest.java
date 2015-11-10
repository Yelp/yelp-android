package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DealOptionTest extends EntityTest {

    public DealOptionTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode dealOptionNode = this.businessResponseJsonNode.path("deals").get(0).path("options").get(0);
        DealOption dealOption = this.objectMapper.readValue(dealOptionNode.toString(),
                DealOption.class
        );

        Assert.assertEquals(
                dealOptionNode.path("formatted_original_price").textValue(),
                dealOption.formattedOriginalPrice()
        );
        Assert.assertEquals(dealOptionNode.path("formatted_price").textValue(), dealOption.formattedPrice());
        Assert.assertEquals(dealOptionNode.path("is_quantity_limited").asBoolean(), dealOption.isQuantityLimited());
        Assert.assertEquals(new Integer(dealOptionNode.path("original_price").asInt()), dealOption.originalPrice());
        Assert.assertEquals(new Integer(dealOptionNode.path("price").asInt()), dealOption.price());
        Assert.assertEquals(dealOptionNode.path("purchase_url").textValue(), dealOption.purchaseUrl());
        Assert.assertEquals(new Integer(dealOptionNode.path("remaining_count").asInt()), dealOption.remainingCount());
        Assert.assertEquals(dealOptionNode.path("title").textValue(), dealOption.title());
    }
}
