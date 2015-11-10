package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GiftCertificateOptionTest extends EntityTest {

    public GiftCertificateOptionTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String giftCertificateOptionString = this.businessResponseJsonNode
                .path("gift_certificates").get(0).path("options").get(0).toString();
        try {
            GiftCertificateOption giftCertificateOption = this.objectMapper.readValue(
                    giftCertificateOptionString,
                    GiftCertificateOption.class
            );

            Assert.assertEquals("$25", giftCertificateOption.formattedPrice());
            Assert.assertEquals(new Integer(2500), giftCertificateOption.price());

        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }
}
