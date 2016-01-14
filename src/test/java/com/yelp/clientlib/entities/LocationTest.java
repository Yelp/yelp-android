package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.util.JsonTestUtils;
import com.yelp.clientlib.util.SerializationTestUtils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class LocationTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode locationNode = JsonTestUtils.getBusinessResponseJsonNode().path("location");
        Location location = JsonTestUtils.deserializeJson(locationNode.toString(), Location.class);

        Assert.assertEquals(1, location.address().size());
        Assert.assertEquals(locationNode.path("city").textValue(), location.city());
        Assert.assertNotNull(location.coordinate());
        Assert.assertEquals(locationNode.path("country_code").textValue(), location.countryCode());
        Assert.assertEquals(locationNode.path("cross_streets").textValue(), location.crossStreets());
        Assert.assertEquals(3, location.displayAddress().size());
        Assert.assertEquals(new Double(locationNode.path("geo_accuracy").asDouble()), location.geoAccuracy());
        Assert.assertEquals(2, location.neighborhoods().size());
        Assert.assertEquals(locationNode.path("postal_code").textValue(), location.postalCode());
        Assert.assertEquals(locationNode.path("state_code").textValue(), location.stateCode());
    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode locationNode = JsonTestUtils.getBusinessResponseJsonNode().path("location");
        Location location = JsonTestUtils.deserializeJson(locationNode.toString(), Location.class);

        byte[] bytes = SerializationTestUtils.serialize(location);
        Assert.assertEquals(location, SerializationTestUtils.deserialize(bytes, Location.class));
    }
}
