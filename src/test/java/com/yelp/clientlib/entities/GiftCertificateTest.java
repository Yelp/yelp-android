package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GiftCertificateTest extends EntityTest {

    public GiftCertificateTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String giftCertificatesString = this.businessResponseJsonNode
                .path("gift_certificates").get(0).toString();

        try {
            GiftCertificate giftCertificate = this.objectMapper.readValue(
                    giftCertificatesString,
                    GiftCertificate.class
            );

            Assert.assertEquals("ZZy5EwrI3wyHw8y54jZruA", giftCertificate.id());
            Assert.assertEquals(
                    "http://www.yelp.com/gift-certificates/some-donut-place-pasadena",
                    giftCertificate.url()
            );
            Assert.assertEquals(
                    "http://s3-media4.ak.yelpcdn.com/bphoto/Hv5vsWpqeaUKepr9nffJnw/m.jpg",
                    giftCertificate.imageUrl()
            );
            Assert.assertEquals("USD", giftCertificate.currencyCode());
            Assert.assertEquals("CREDIT", giftCertificate.unusedBalances());
            Assert.assertTrue(giftCertificate.options().get(0) instanceof GiftCertificateOption);

        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }
}
