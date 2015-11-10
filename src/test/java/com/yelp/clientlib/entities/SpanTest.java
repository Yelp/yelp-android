package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SpanTest extends EntityTest {

    public SpanTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String spanString = "{\"latitude_delta\": 100.123321, \"longitude_delta\": -10.123321}";
        Span span = this.objectMapper.readValue(spanString, Span.class);

        Assert.assertEquals(new Double(100.123321), span.latitudeDelta());
        Assert.assertEquals(new Double(-10.123321), span.longitudeDelta());
    }
}
