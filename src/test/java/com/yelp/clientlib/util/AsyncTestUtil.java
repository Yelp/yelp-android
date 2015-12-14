package com.yelp.clientlib.util;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncTestUtil {
    public static final int DEFAULT_ASYNC_TIMEOUT_MILLISECONDS = 1000;

    public static void waitAndCheckAsyncRequestStatus(ArrayList returnedObjectWrapper)
            throws InterruptedException {
        waitAndCheckAsyncRequestStatus(returnedObjectWrapper, DEFAULT_ASYNC_TIMEOUT_MILLISECONDS);
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
