package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RegionTest extends EntityTest {

    public RegionTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String regionString = "{" +
                "\"center\": {\"latitude\": 50.123321, \"longitude\": -50.123321}," +
                "\"span\": {\"latitude_delta\": 100.123321, \"longitude_delta\": -10.123321}" +
                "}";
        Region region = this.objectMapper.readValue(regionString, Region.class);

        Assert.assertEquals(new Double(50.123321), region.center().latitude());
        Assert.assertEquals(new Double(-50.123321), region.center().longitude());
        Assert.assertEquals(new Double(100.123321), region.span().latitudeDelta());
        Assert.assertEquals(new Double(-10.123321), region.span().longitudeDelta());
    }
}
