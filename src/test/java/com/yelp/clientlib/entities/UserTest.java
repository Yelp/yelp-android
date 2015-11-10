package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UserTest extends EntityTest {

    public UserTest() throws IOException {
        super();
    }

    @Test
    public void testDeserializeFromJson() throws IOException {
        String userString = "{" +
                "\"id\": \"Bogus_id\"," +
                "\"image_url\": \"http://yelp.bogus.image.jpg\"," +
                "\"name\": \"Darwin S.\"" +
                "}";
        User user = this.objectMapper.readValue(userString, User.class);

        Assert.assertEquals("Bogus_id", user.id());
        Assert.assertEquals("http://yelp.bogus.image.jpg", user.imageUrl());
        Assert.assertEquals("Darwin S.", user.name());
    }
}
