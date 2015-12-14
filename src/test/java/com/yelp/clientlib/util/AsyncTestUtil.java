package com.yelp.clientlib.util;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class AsyncTestUtil {

    public static int defaultAsyncRequestTimeoutMilliseconds = 1000;

    public static void waitAndCheckAsyncRequestStatus(ArrayList returnedObjectWrapper)
            throws InterruptedException {
        waitAndCheckAsyncRequestStatus(returnedObjectWrapper, defaultAsyncRequestTimeoutMilliseconds);
    }

    public static void waitAndCheckAsyncRequestStatus(ArrayList returnedObjectWrapper, int timeoutMilliseconds)
            throws InterruptedException {
        CountDownLatch countDownTimer = new CountDownLatch(1);

        countDownTimer.await(timeoutMilliseconds, TimeUnit.MILLISECONDS);
        Assert.assertFalse(
                String.format("No response got in %d milliseconds.", timeoutMilliseconds),
                returnedObjectWrapper.isEmpty()
        );
    }
}
