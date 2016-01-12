package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import oauth.signpost.http.HttpRequest;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

/**
 * {@link HttpOAuthConsumer} is an {@link oauth.signpost.OAuthConsumer} implementation to sign OkHttp {@link Request}s.
 */
public class HttpOAuthConsumer extends OkHttpOAuthConsumer {

    public HttpOAuthConsumer(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }


    /**
     * {@link OkHttpOAuthConsumer} doesn't handle characters required for encoding {@link com.yelp.clientlib.entities
     * .options.BoundingBoxOptions}, {@link HttpRequestAdapter} encodes those characters so oauth-signpost can sign
     * requests correctly.
     *
     * @param request the native HTTP request instance
     * @return the adapted request
     */
    @Override
    protected HttpRequest wrap(Object request) {
        return new HttpRequestAdapter((Request) request);
    }
}