package com.yelp.clientlib.entities;

import java.util.HashMap;

/**
 * Base class for query options.
 *
 * Every query options class should extend from this base class to be consumed by Retrofit as a query parameter map.
 * @see {@link retrofit.http.QueryMap}
 */
public class QueryOptions extends HashMap<String, Object> {

    public void set(String key, Object value) {
        put(key, value);
    }

    public String getString(String key) {
        return (String) this.get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) this.get(key);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) this.get(key);
    }
}
