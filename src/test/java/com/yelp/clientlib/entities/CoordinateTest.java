package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CoordinateTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode coordinateNode = JsonTestUtils.getBusinessResponseJsonNode().path("location").path("coordinate");
        Coordinate coordinate = JsonTestUtils.deserializeJson(coordinateNode.toString(), Coordinate.class);

        Assert.assertEquals(new Double(coordinateNode.path("latitude").asDouble()), coordinate.latitude());
        Assert.assertEquals(new Double(coordinateNode.path("longitude").asDouble()), coordinate.longitude());
    }
}
