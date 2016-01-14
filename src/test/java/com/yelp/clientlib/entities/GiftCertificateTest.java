package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.util.JsonTestUtils;
import com.yelp.clientlib.util.SerializationTestUtils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GiftCertificateTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode giftCertificatesNode = JsonTestUtils.getBusinessResponseJsonNode().path("gift_certificates").get(0);

        GiftCertificate giftCertificate = JsonTestUtils.deserializeJson(
                giftCertificatesNode.toString(),
                GiftCertificate.class
        );

        Assert.assertEquals(giftCertificatesNode.path("id").textValue(), giftCertificate.id());
        Assert.assertEquals(giftCertificatesNode.path("url").textValue(), giftCertificate.url());
        Assert.assertEquals(giftCertificatesNode.path("image_url").textValue(), giftCertificate.imageUrl());
        Assert.assertEquals(giftCertificatesNode.path("currency_code").textValue(), giftCertificate.currencyCode());
        Assert.assertEquals(giftCertificatesNode.path("unused_balances").textValue(), giftCertificate.unusedBalances());

        // GiftCertificateOption is tested in it's own test.
        Assert.assertNotNull(giftCertificate.options().get(0));
    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode giftCertificatesNode = JsonTestUtils.getBusinessResponseJsonNode().path("gift_certificates").get(0);
        GiftCertificate giftCertificate = JsonTestUtils.deserializeJson(
                giftCertificatesNode.toString(),
                GiftCertificate.class
        );

        byte[] bytes = SerializationTestUtils.serialize(giftCertificate);
        Assert.assertEquals(giftCertificate, SerializationTestUtils.deserialize(bytes, GiftCertificate.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoId() throws IOException {
        GiftCertificate.builder().id(null).build();
    }
}
