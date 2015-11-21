package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UserTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode userNode = JsonTestUtils.getBusinessResponseJsonNode().path("reviews").get(0).path("user");
        User user = JsonTestUtils.deserializeJson(userNode.toString(), User.class);

        Assert.assertEquals(userNode.path("id").textValue(), user.id());
        Assert.assertEquals(userNode.path("image_url").textValue(), user.imageUrl());
        Assert.assertEquals(userNode.path("name").textValue(), user.name());
    }
}
