package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DealOptionTest extends EntityTest {

    public DealOptionTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String dealOptionString = this.businessResponseJsonNode
                .path("deals").get(0).path("options").get(0).toString();
        try {
            DealOption dealOption = this.objectMapper.readValue(dealOptionString, DealOption.class);

            Assert.assertEquals("$20", dealOption.formattedOriginalPrice());
            Assert.assertEquals("$10", dealOption.formattedPrice());
            Assert.assertEquals(true, dealOption.isQuantityLimited());
            Assert.assertEquals(new Integer(2000), dealOption.originalPrice());
            Assert.assertEquals(new Integer(1000), dealOption.price());
            Assert.assertEquals(
                    "http://www.yelp.com/deal/cC24ccQGIH8mowfu5Vbe0Q/view",
                    dealOption.purchaseUrl()
            );
            Assert.assertEquals(new Integer(36), dealOption.remainingCount());
            Assert.assertEquals("$10 for $20 voucher", dealOption.title());

        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }
}
