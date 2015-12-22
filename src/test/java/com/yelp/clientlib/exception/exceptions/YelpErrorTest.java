package com.yelp.clientlib.exception.exceptions;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class YelpErrorTest {

    @Test
    public void testConstructorNoCauseNoMessage(){
        YelpError error = new YelpError();

        Assert.assertNull(error.getMessage());
        Assert.assertNull(error.getCause());
    }

    @Test
    public void testConstructorNoPassInCause(){
        String errorMessage = "It's a good error";
        YelpError error = new YelpError(errorMessage);

        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertNull(error.getCause());
    }

    @Test
    public void testConstructorNoPassInMessageUseInnerErrorString(){
        Throwable innerError = new IOException();
        YelpError error = new YelpError(innerError);

        Assert.assertEquals(innerError, error.getCause());
        Assert.assertEquals(innerError.toString(), error.getMessage());
    }

    @Test
    public void testConstructorWithCauseAndMessage(){
        String errorMessage = "It's a good error";
        Throwable innerError = new IOException();
        YelpError error = new YelpError(errorMessage, innerError);

        Assert.assertEquals(errorMessage, error.getMessage());
        Assert.assertEquals(innerError, error.getCause());
    }
}
