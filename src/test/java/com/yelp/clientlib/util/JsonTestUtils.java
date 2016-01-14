package com.yelp.clientlib.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonTestUtils {
    public static final String BUSINESS_RESPONSE_JSON_FILENAME = "businessResponse.json";

    public static final String SEARCH_RESPONSE_JSON_FILENAME = "searchResponse.json";

    public static JsonNode getBusinessResponseJsonNode() throws IOException {
        return getJsonNodeFromFile(BUSINESS_RESPONSE_JSON_FILENAME);
    }

    public static JsonNode getSearchResponseJsonNode() throws IOException {
        return getJsonNodeFromFile(SEARCH_RESPONSE_JSON_FILENAME);
    }

    public static JsonNode getJsonNodeFromFile(String filename) throws IOException {
        File jsonFile = new File(JsonTestUtils.class.getClassLoader().getResource(filename).getFile());
        return new ObjectMapper().readTree(jsonFile);
    }

    public static <T> T deserializeJson(String content, Class<T> valueType) throws IOException {
        return new ObjectMapper().readValue(content, valueType);
    }
}
