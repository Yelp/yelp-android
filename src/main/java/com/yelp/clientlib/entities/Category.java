package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.io.IOException;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CategoryDeserializer.class)
public abstract class Category {

    public abstract String alias();

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

/**
 * Custom deserializer for Category. The JSON string returned for Category is formatted as an array like
 * "["Bar", "bar"]" which does not fit into the default Jackson object deserializer which expects "{" as the first
 * character.
 */
class CategoryDeserializer extends JsonDeserializer<Category> {
    @Override
    public Category deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get(0).textValue();
        String alias = node.get(1).textValue();

        return Category.builder().name(name).alias(alias).build();
    }
}

