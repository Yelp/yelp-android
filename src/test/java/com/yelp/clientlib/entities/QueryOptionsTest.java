package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

public class QueryOptionsTest {

    @Test
    public void testGetString() {
        String key = "this_is_key";
        String value = "this_is_value";

        QueryOptions params = new QueryOptions();
        params.set(key, value);
        Assert.assertEquals(value, params.getString(key));
    }

    @Test
    public void testGetStringUnSetValue() {
        Assert.assertNull(new QueryOptions().getString("non_exist_key"));
    }

    @Test
    public void testGetInteger() {
        String key = "this_is_key";
        Integer value = 1000;

        QueryOptions params = new QueryOptions();
        params.set(key, value);
        Assert.assertEquals(value, params.getInteger(key));
    }

    @Test
    public void testGetIntegerUnSetValue() {
        Assert.assertNull(new QueryOptions().getInteger("non_exist_key"));
    }

    @Test
    public void testGetBoolean() {
        String key = "this_is_key";
        Boolean value = true;

        QueryOptions params = new QueryOptions();
        params.set(key, value);
        Assert.assertEquals(value, params.getBoolean(key));
    }

    @Test
    public void testGetBooleanUnSetValue() {
        Assert.assertNull(new QueryOptions().getBoolean("non_exist_key"));
    }
}
