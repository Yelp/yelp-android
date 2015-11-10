package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CoordinateTest extends EntityTest {

    public CoordinateTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode coordinateNode = this.businessResponseJsonNode.path("location").path("coordinate");
        Coordinate coordinate = this.objectMapper.readValue(coordinateNode.toString(), Coordinate.class);

        Assert.assertEquals(new Double(coordinateNode.path("latitude").asDouble()), coordinate.latitude());
        Assert.assertEquals(new Double(coordinateNode.path("longitude").asDouble()), coordinate.longitude());
    }
}
