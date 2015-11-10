package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class LocationTest extends EntityTest {

    public LocationTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode locationNode = this.businessResponseJsonNode.path("location");
        Location location = this.objectMapper.readValue(locationNode.toString(), Location.class);

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
}
