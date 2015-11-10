package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class EntityTest {
    public static final String BUSINESS_RESPONSE_JSON_FILENAME = "businessResponse.json";

    protected ObjectMapper objectMapper;
    protected JsonNode businessResponseJsonNode;

    public EntityTest() throws IOException {
        this.objectMapper = new ObjectMapper();

        File businessResponseJsonFile = new File(
                getClass().getClassLoader().getResource(BUSINESS_RESPONSE_JSON_FILENAME).getFile()
        );
        this.businessResponseJsonNode = objectMapper.readTree(businessResponseJsonFile);
    }
}
