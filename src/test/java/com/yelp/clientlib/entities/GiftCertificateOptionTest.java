package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.util.JsonTestUtils;
import com.yelp.clientlib.util.SerializationTestUtils;

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

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode giftCertificateOptionNode = JsonTestUtils.getBusinessResponseJsonNode()
                .path("gift_certificates").get(0).path("options").get(0);
        GiftCertificateOption giftCertificateOption = JsonTestUtils.deserializeJson(
                giftCertificateOptionNode.toString(),
                GiftCertificateOption.class
        );

        byte[] bytes = SerializationTestUtils.serialize(giftCertificateOption);
        Assert.assertEquals(
                giftCertificateOption,
                SerializationTestUtils.deserialize(bytes, GiftCertificateOption.class)
        );
    }
}
