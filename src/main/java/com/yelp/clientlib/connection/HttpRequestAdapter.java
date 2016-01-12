package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import java.io.IOException;

import oauth.signpost.http.HttpRequest;
import se.akerfeldt.okhttp.signpost.OkHttpRequestAdapter;

/**
 * A {@link HttpRequest} implementation that wraps an OkHttp {@link Request} object. This is used by oauth-signpost to
 * read the {@link Request} and sign it.
 */
public class HttpRequestAdapter extends OkHttpRequestAdapter {
    public HttpRequestAdapter(Request request) {
        super(request);
    }

    /**
     * Get encoded URL of the request.
     *
     * {@link OkHttpRequestAdapter#getRequestUrl()} doesn't handle characters required for encoding {@link com.yelp
     * .clientlib.entities.options.BoundingBoxOptions}. This method encodes those characters so oauth-signpost can sign
     * it correctly.
     *
     * @return encoded request URL.
     */
    @Override
    public String getRequestUrl() {
        String url = super.getRequestUrl();

        return url.replace("|", "%7C");
    }
}
