package com.yelp.clientlib.util;

import com.squareup.okhttp.Dispatcher;
import com.squareup.okhttp.OkHttpClient;
import com.yelp.clientlib.connection.YelpAPIFactory;

import org.junit.Assert;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import junitx.util.PrivateAccessor;

public class AsyncTestUtils {

    /**
     * Make a {@link YelpAPIFactory} to send HTTP requests in main thread so we can verify the test results easily.
     * Retrofit uses {@link OkHttpClient} for the underlying HTTP calls, this method replaces the {@link Dispatcher}
     * in {@link OkHttpClient} so an {@link ExecutorService} runs jobs in main thread will be used to send HTTP
     * requests.
     */
    public static YelpAPIFactory setToRunInMainThread(YelpAPIFactory yelpAPIFactory) {
        Dispatcher synchronousDispatcher = new Dispatcher(newSynchronousExecutorService());

        try {
            OkHttpClient httpClient = (OkHttpClient) PrivateAccessor.getField(yelpAPIFactory, "httpClient");
            httpClient.setDispatcher(synchronousDispatcher);
        } catch (NoSuchFieldException e) {
            Assert.fail(e.toString());
        }

        return yelpAPIFactory;
    }

    /**
     * Create an {@link ExecutorService} which runs jobs in main thread.
     */
    public static ExecutorService newSynchronousExecutorService() {
        return new AbstractExecutorService() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }

            @Override
            public void shutdown() {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<Runnable> shutdownNow() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isShutdown() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isTerminated() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                throw new UnsupportedOperationException();
            }
        };
    }
}
