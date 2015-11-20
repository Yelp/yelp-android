package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.io.IOException;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CategoryDeserializer.class)
public abstract class Category {

    @Nullable
    public abstract String alias();

    @Nullable
    public abstract String name();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder alias(String alias);

        public abstract Builder name(String name);

        public abstract Category build();
    }

    public static Builder builder() {
        return new AutoValue_Category.Builder();
    }
}

class CategoryDeserializer extends JsonDeserializer<Category> {
    /* We need this custom deserializer for Category. The JSON string returned is "["Bar", "bar"]" which starts with
    the array annotation: "[" but the default deserializer expects a "{".

    TODO: add a proper description for the reason why we need a custom deserializer here.
    */

    @Override
    public Category deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get(0).textValue();
        String alias = node.get(1).textValue();

        return Category.builder().name(name).alias(alias).build();
    }
}

