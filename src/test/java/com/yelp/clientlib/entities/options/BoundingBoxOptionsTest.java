package com.yelp.clientlib.entities.options;

import org.junit.Assert;
import org.junit.Test;

public class BoundingBoxOptionsTest {
    Double swLatitude = 11.11111;
    Double swLongitude = 22.11111;
    Double neLatitude = 33.11111;
    Double neLongitude = 44.11111;

    @Test
    public void testBuilder() {
        BoundingBoxOptions bounds = BoundingBoxOptions.builder()
                .swLatitude(swLatitude)
                .swLongitude(swLongitude)
                .neLatitude(neLatitude)
                .neLongitude(neLongitude).build();

        Assert.assertEquals(swLatitude, bounds.swLatitude());
        Assert.assertEquals(swLongitude, bounds.swLongitude());
        Assert.assertEquals(neLatitude, bounds.neLatitude());
        Assert.assertEquals(neLongitude, bounds.neLongitude());
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetSwLatitudeRaiseException() {
        BoundingBoxOptions.builder()
                .swLongitude(swLongitude)
                .neLatitude(neLatitude)
                .neLongitude(neLongitude)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetSwLongitudeRaiseException() {
        BoundingBoxOptions.builder()
                .swLatitude(swLatitude)
                .neLatitude(neLatitude)
                .neLongitude(neLongitude)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetNeLatitudeRaiseException() {
        BoundingBoxOptions.builder()
                .swLatitude(swLatitude)
                .swLongitude(swLongitude)
                .neLongitude(neLongitude)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetNeLongitudeRaiseException() {
        BoundingBoxOptions.builder()
                .swLatitude(swLatitude)
                .swLongitude(swLongitude)
                .neLatitude(neLatitude)
                .build();
    }
}
