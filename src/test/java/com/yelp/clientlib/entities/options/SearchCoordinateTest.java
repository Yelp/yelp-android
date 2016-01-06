package com.yelp.clientlib.entities.options;

import org.junit.Assert;
import org.junit.Test;

public class SearchCoordinateTest {
    Double latitude = 11.11111;
    Double longitude = 22.11111;

    @Test
    public void testBuilder() {
        SearchCoordinate coordinate = SearchCoordinate.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();

        Assert.assertEquals(latitude, coordinate.latitude());
        Assert.assertEquals(longitude, coordinate.longitude());
    }

    @Test
    public void testToString() {
        SearchCoordinate coordinate = SearchCoordinate.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
        Assert.assertEquals(latitude + "," + longitude, coordinate.toString());
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetLatitudeRaiseException() {
        SearchCoordinate.builder()
                .longitude(longitude)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetLongitudeRaiseException() {
        SearchCoordinate.builder()
                .latitude(latitude)
                .build();
    }
}