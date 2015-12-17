package com.yelp.clientlib.util;

import retrofit.Callback;

public class AsyncTestUtil {
    public static final int DEFAULT_ASYNC_TIMEOUT_MILLISECONDS = 1000;

    /**
     * Don't use in production, this function doesn't check condition when the thread is woke up.
     */
    public static void waitForCallBack(Callback callback) throws InterruptedException {
        waitForCallBack(callback, DEFAULT_ASYNC_TIMEOUT_MILLISECONDS);
    }

    /**
     * Don't use in production, this function doesn't check condition when the thread is woke up.
     */
    public static void waitForCallBack(Callback callback, int timeoutMilliseconds) throws InterruptedException {
        synchronized (callback) {
            callback.wait(timeoutMilliseconds);
        }
    }

    public static void callBackIsDone(Callback callback) {
        synchronized (callback) {
            callback.notifyAll();
        }
    }
}
