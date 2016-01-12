package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestAdapterTest {

    @Test
    public void testGetRequestUrlEncodePipe() {
        String url = "https://api.yelp.com/v2/search?bounds=11.111111,22.222222|33.333333,44.444444&term=yelp";
        Request request = new Request.Builder().url(url).build();
        HttpRequestAdapter adapter = new HttpRequestAdapter(request);
        Assert.assertEquals(
                "https://api.yelp.com/v2/search?bounds=11.111111,22.222222%7C33.333333,44.444444&term=yelp",
                adapter.getRequestUrl()
        );
    }
}
