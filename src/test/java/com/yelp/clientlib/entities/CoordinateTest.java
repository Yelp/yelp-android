package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.util.JsonTestUtils;
import com.yelp.clientlib.util.SerializationTestUtils;

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

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode coordinateNode = JsonTestUtils.getBusinessResponseJsonNode().path("location").path("coordinate");
        Coordinate coordinate = JsonTestUtils.deserializeJson(coordinateNode.toString(), Coordinate.class);

        byte[] bytes = SerializationTestUtils.serialize(coordinate);
        Assert.assertEquals(coordinate, SerializationTestUtils.deserialize(bytes, Coordinate.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoLatitude() throws IOException {
        Coordinate.builder().longitude(123.123123).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoLongitude() throws IOException {
        Coordinate.builder().latitude(123.123123).build();
    }
}
