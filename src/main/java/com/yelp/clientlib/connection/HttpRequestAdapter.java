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

    private Request request;

    public HttpRequestAdapter(Request request) {
        super(request);
        this.request = request;
    }

    /**
     * Get encoded URL of the request.
     *
     * <p>This method converts URI back to URL. Since uri() forbids certain characters like '[' and '|', the URI
     * returned by uri() may escape more characters than directly using {@link Request#httpUrl()}.</p>
     *
     * @return encoded request URL.
     */
    @Override
    public String getRequestUrl() {
        try {
            return request.uri().toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
