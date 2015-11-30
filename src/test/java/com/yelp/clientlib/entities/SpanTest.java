package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SpanTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode spanNode = JsonTestUtils.getSearchResponseJsonNode().path("region").path("span");
        Span span = JsonTestUtils.deserializeJson(spanNode.toString(), Span.class);

        Assert.assertEquals(new Double(spanNode.path("latitude_delta").asDouble()), span.latitudeDelta());
        Assert.assertEquals(new Double(spanNode.path("longitude_delta").asDouble()), span.longitudeDelta());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoLatitudeDelta() throws IOException {
        Span.builder().longitudeDelta(50.123123).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoLongitude() throws IOException {
        Span.builder().latitudeDelta(50.123123).build();
    }
}
