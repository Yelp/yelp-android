package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.utils.JsonTestUtils;
import com.yelp.clientlib.utils.SerializationTestUtils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DealOptionTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode dealOptionNode = JsonTestUtils.getBusinessResponseJsonNode()
                .path("deals").get(0).path("options").get(0);
        DealOption dealOption = JsonTestUtils.deserializeJson(dealOptionNode.toString(), DealOption.class);

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

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode dealOptionNode = JsonTestUtils.getBusinessResponseJsonNode()
                .path("deals").get(0).path("options").get(0);
        DealOption dealOption = JsonTestUtils.deserializeJson(dealOptionNode.toString(), DealOption.class);

        byte[] bytes = SerializationTestUtils.serialize(dealOption);
        Assert.assertEquals(dealOption, SerializationTestUtils.deserialize(bytes, DealOption.class));
    }
}
