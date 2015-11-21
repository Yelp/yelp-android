package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RegionTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode regionNode = JsonTestUtils.getSearchResponseJsonNode().path("region");
        Region region = JsonTestUtils.deserializeJson(regionNode.toString(), Region.class);

        // Coordinate and Span are tested in it's own tests.
        Assert.assertNotNull(region.center());
        Assert.assertNotNull(region.span());
    }
}
