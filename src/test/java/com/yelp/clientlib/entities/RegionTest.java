package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.utils.JsonTestUtils;
import com.yelp.clientlib.utils.SerializationTestUtils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RegionTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode regionNode = JsonTestUtils.getSearchResponseJsonNode().path("region");
        Region region = JsonTestUtils.deserializeJson(regionNode.toString(), Region.class);

        // Coordinate and Span are tested in their own tests.
        Assert.assertNotNull(region.center());
        Assert.assertNotNull(region.span());
    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode regionNode = JsonTestUtils.getSearchResponseJsonNode().path("region");
        Region region = JsonTestUtils.deserializeJson(regionNode.toString(), Region.class);

        byte[] bytes = SerializationTestUtils.serialize(region);
        Assert.assertEquals(region, SerializationTestUtils.deserialize(bytes, Region.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoCenter() throws IOException {
        Span span = Span.builder().latitudeDelta(50.123).longitudeDelta(50.123).build();
        Region.builder().span(span).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoSpan() throws IOException {
        Coordinate center = Coordinate.builder().latitude(123.123123).longitude(123.123123).build();
        Region.builder().center(center).build();
    }
}
