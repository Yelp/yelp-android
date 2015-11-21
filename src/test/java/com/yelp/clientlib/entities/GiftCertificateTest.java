package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

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

        // GiftCertificateOption is tested in it's own tests.
        Assert.assertNotNull(giftCertificate.options().get(0));
    }
}
