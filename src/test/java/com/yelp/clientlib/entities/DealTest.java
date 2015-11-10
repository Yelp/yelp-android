package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DealTest extends EntityTest {

    public DealTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String dealString = this.businessResponseJsonNode.path("deals").get(0).toString();

        try {
            Deal deal = this.objectMapper.readValue(dealString, Deal.class);

            Assert.assertNull(deal.additionalRestrictions());
            Assert.assertEquals("USD", deal.currencyCode());
            Assert.assertNull(deal.id());
            Assert.assertEquals(
                    "http://s3-media4.ak.yelpcdn.com/dphoto/ShQGf5qi-52HwPiKyZTZ3w/m.jpg",
                    deal.imageUrl()
            );
            Assert.assertNull(deal.importantRestriction());
            Assert.assertEquals(true, deal.isPopular());
            Assert.assertTrue(deal.options().get(0) instanceof DealOption);
            Assert.assertNull(deal.timeEnd());
            Assert.assertEquals(new Long(1317414369), deal.timeStart());
            Assert.assertEquals("$10 for $20 voucher", deal.title());
            Assert.assertEquals(
                    "http://www.yelp.com/biz/urban-curry-san-francisco?deal=1",
                    deal.url()
            );
            Assert.assertNull(deal.whatYouGet());

        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }
}
