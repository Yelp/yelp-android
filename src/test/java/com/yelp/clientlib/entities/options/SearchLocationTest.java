package com.yelp.clientlib.entities.options;


import org.junit.Assert;
import org.junit.Test;

public class SearchLocationTest {
    Double latitude = 11.11111;
    Double longitude = 22.11111;
    Double accuracy = 9.5;
    Double altitude = 100.11;
    Double altitudeAccuracy = 9.5;

    @Test
    public void testBuilder() {
        SearchLocation location = SearchLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .altitude(altitude)
                .altitudeAccuracy(altitudeAccuracy)
                .build();

        Assert.assertEquals(latitude, location.latitude());
        Assert.assertEquals(longitude, location.longitude());
        Assert.assertEquals(accuracy, location.accuracy());
        Assert.assertEquals(altitude, location.altitude());
        Assert.assertEquals(altitudeAccuracy, location.altitudeAccuracy());
    }

    @Test
    public void testToStringWithLatLong() {
        SearchLocation location = SearchLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();

        Assert.assertEquals(latitude + "," + longitude, location.toString());
    }

    @Test
    public void testToStringWithLatLongAccuracy() {
        SearchLocation location = SearchLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .build();

        Assert.assertEquals(latitude + "," + longitude + "," + accuracy, location.toString());
    }

    @Test
    public void testToStringWithLatLongAccuracyAltitude() {
        SearchLocation location = SearchLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .altitude(altitude)
                .build();

        Assert.assertEquals(
                latitude + "," + longitude + "," + accuracy + "," + altitude,
                location.toString()
        );
    }

    @Test
    public void testToStringWithAllFields() {
        SearchLocation location = SearchLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .accuracy(accuracy)
                .altitude(altitude)
                .altitudeAccuracy(altitudeAccuracy)
                .build();

        Assert.assertEquals(
                latitude + "," + longitude + "," + accuracy + "," + altitude + "," +
                        altitudeAccuracy,
                location.toString()
        );
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetLatitudeRaiseException() {
        SearchLocation.builder()
                .longitude(longitude)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNonSetLongitudeRaiseException() {
        SearchLocation.builder()
                .latitude(latitude)
                .build();
    }

    @Test
    public void testNonSetNullableValueRaiseNoException() {
        SearchLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}