package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import oauth.signpost.http.HttpRequest;
import se.akerfeldt.okhttp.signpost.OkHttpRequestAdapter;

/**
 * A {@link HttpRequest} implementation that wraps an OkHttp {@link Request} object. This is used by oauth-signpost to
 * read the {@link Request} and sign it.
 */
public class HttpRequestAdapter extends OkHttpRequestAdapter {

    private Request request;

    public HttpRequestAdapter(Request request) {
        super(request);
        this.request = request;
    }

    /**
     * Get encoded URL of the request.
     *
     * @return encoded request URL.
     */
    @Override
    public String getRequestUrl() {
        // Because URI forbids certain characters like '[' and '|', using uri() may escape more characters than the
        // original URL.
        return request.httpUrl().uri().toString();
    }
}
