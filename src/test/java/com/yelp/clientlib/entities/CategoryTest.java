package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CategoryTest extends EntityTest {

    public CategoryTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode categoryNode = this.businessResponseJsonNode.path("categories").get(0);
        Category category = this.objectMapper.readValue(categoryNode.toString(), Category.class);

        Assert.assertEquals(categoryNode.get(0).textValue(), category.name());
        Assert.assertEquals(categoryNode.get(1).textValue(), category.alias());
    }
}
