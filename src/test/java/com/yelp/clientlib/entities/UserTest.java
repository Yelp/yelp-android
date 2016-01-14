package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.yelp.clientlib.util.JsonTestUtils;
import com.yelp.clientlib.util.SerializationTestUtil;

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

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        JsonNode userNode = JsonTestUtils.getBusinessResponseJsonNode().path("reviews").get(0).path("user");
        User user = JsonTestUtils.deserializeJson(userNode.toString(), User.class);

        byte[] bytes = SerializationTestUtil.serialize(user);
        Assert.assertEquals(user, SerializationTestUtil.deserialize(bytes, User.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailedWithNoId() throws IOException {
        User.builder().id(null).build();
    }
}
