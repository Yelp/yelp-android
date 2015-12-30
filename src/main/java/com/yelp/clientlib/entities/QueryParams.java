package com.yelp.clientlib.entities;

import java.util.HashMap;

public class QueryParams extends HashMap<String, Object> {

    String getString(String key) {
        return (String) this.get(key);
    }

    Integer getInteger(String key) {
        return (Integer) this.get(key);
    }

    Boolean getBoolean(String key) {
        return (Boolean) this.get(key);
    }
}
