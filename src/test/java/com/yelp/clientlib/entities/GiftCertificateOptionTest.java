package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GiftCertificateOptionTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode giftCertificateOptionNode = JsonTestUtils.getBusinessResponseJsonNode()
                .path("gift_certificates").get(0).path("options").get(0);

        GiftCertificateOption giftCertificateOption = JsonTestUtils.deserializeJson(
                giftCertificateOptionNode.toString(),
                GiftCertificateOption.class
        );

        Assert.assertEquals(
                giftCertificateOptionNode.path("formatted_price").textValue(),
                giftCertificateOption.formattedPrice()
        );
        Assert.assertEquals(
                new Integer(giftCertificateOptionNode.path("price").asInt()),
                giftCertificateOption.price()
        );
    }
}
