package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CategoryTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode categoryNode = JsonTestUtils.getBusinessResponseJsonNode().path("categories").get(0);
        Category category = JsonTestUtils.deserializeJson(categoryNode.toString(), Category.class);

        Assert.assertEquals(categoryNode.get(0).textValue(), category.name());
        Assert.assertEquals(categoryNode.get(1).textValue(), category.alias());
    }

    @Test(expected = JsonMappingException.class)
    public void testDeserializationFailedWithNonPairedValue() throws IOException {
        String categoryJsonString = "[\"Restaurant\"]";
        JsonTestUtils.deserializeJson(categoryJsonString, Business.class);
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoAlias() throws IOException {
        Category.builder().name("Restaurant").build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoName() throws IOException {
        Category.builder().alias("restaurant").build();
    }
}
