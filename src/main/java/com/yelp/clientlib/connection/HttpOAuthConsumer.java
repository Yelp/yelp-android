package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import oauth.signpost.http.HttpRequest;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

/**
 * {@code HttpOAuthConsumer} is an {@link oauth.signpost.OAuthConsumer} implementation to sign OkHttp {@link Request}s.
 */
public class HttpOAuthConsumer extends OkHttpOAuthConsumer {

    public HttpOAuthConsumer(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }

    @Override
    protected HttpRequest wrap(Object request) {
        if (!(request instanceof Request)) {
            throw new IllegalArgumentException(
                    "This consumer expects requests of type " + Request.class.getCanonicalName()
            );
        }
        return new HttpRequestAdapter((Request) request);
    }
}