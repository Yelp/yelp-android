package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

public class QueryParamsTest {

    @Test
    public void testGetString() {
        String key = "this_is_key";
        String value = "this_is_value";

        QueryParams params = new QueryParams();
        params.set(key, value);
        Assert.assertEquals(value, params.getString(key));
    }

    @Test
    public void testGetStringUnSetValue() {
        Assert.assertNull(new QueryParams().getString("non_exist_key"));
    }

    @Test
    public void testGetInteger() {
        String key = "this_is_key";
        Integer value = 1000;

        QueryParams params = new QueryParams();
        params.set(key, value);
        Assert.assertEquals(value, params.getInteger(key));
    }

    @Test
    public void testGetIntegerUnSetValue() {
        Assert.assertNull(new QueryParams().getInteger("non_exist_key"));
    }

    @Test
    public void testGetBoolean() {
        String key = "this_is_key";
        Boolean value = true;

        QueryParams params = new QueryParams();
        params.set(key, value);
        Assert.assertEquals(value, params.getBoolean(key));
    }

    @Test
    public void testGetBooleanUnSetValue() {
        Assert.assertNull(new QueryParams().getBoolean("non_exist_key"));
    }
}
