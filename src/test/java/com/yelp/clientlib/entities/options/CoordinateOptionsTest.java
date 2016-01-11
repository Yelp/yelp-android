package com.yelp.clientlib.entities.options;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateOptionsTest {
    Double latitude = 11.11111;
    Double longitude = 22.11111;
    Double accuracy = 9.5;
    Double altitude = 100.11;
    Double altitudeAccuracy = 9.5;

    @Test
    public void testBuilder() {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .altitude(altitude)
                .altitudeAccuracy(altitudeAccuracy)
                .build();

        Assert.assertEquals(latitude, coordinate.latitude());
        Assert.assertEquals(longitude, coordinate.longitude());
        Assert.assertEquals(accuracy, coordinate.accuracy());
        Assert.assertEquals(altitude, coordinate.altitude());
        Assert.assertEquals(altitudeAccuracy, coordinate.altitudeAccuracy());
    }

    @Test
    public void testToStringWithLatLong() {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();

        Assert.assertEquals(latitude + "," + longitude + ",,,", coordinate.toString());
    }

    @Test
    public void testToStringWithLatLongAccuracy() {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .build();

        Assert.assertEquals(latitude + "," + longitude + "," + accuracy + ",,", coordinate.toString());
    }

    @Test
    public void testToStringWithLatLongAltitude() {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .altitude(altitude)
                .build();

        Assert.assertEquals(latitude + "," + longitude + ",," + altitude + ",", coordinate.toString());
    }

    @Test
    public void testToStringWithLatLongAccuracyAltitude() {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .altitude(altitude)
                .build();

        Assert.assertEquals(
                latitude + "," + longitude + "," + accuracy + "," + altitude + ",",
                coordinate.toString()
        );
    }

    @Test
    public void testToStringWithAllFields() {
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .altitude(altitude)
                .altitudeAccuracy(altitudeAccuracy)
                .build();

        Assert.assertEquals(
                latitude + "," + longitude + "," + accuracy + "," + altitude + "," + altitudeAccuracy,
                coordinate.toString()
        );
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetLatitudeRaiseException() {
        CoordinateOptions.builder()
                .longitude(longitude)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetLongitudeRaiseException() {
        CoordinateOptions.builder()
                .latitude(latitude)
                .build();
    }

    @Test
    public void testNonSetNullableValueRaiseNoException() {
        CoordinateOptions.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}