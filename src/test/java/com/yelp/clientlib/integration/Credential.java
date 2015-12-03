package com.yelp.clientlib.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.auto.value.AutoValue;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;

/**
 * Util class to get test credentials from credentials.yaml file.
 *
 * TODO: Move this class to other directory so src/java/test only contains unit-tests related files.
 */
@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Credential.Builder.class)
public abstract class Credential {

    private static final String CREDS_CONFIG_FILE_NAME = "credentials.yaml";

    private static Credential instance;

    public static String getConsumerKey() {
        return getCredential().consumerKey();
    }

    public static String getConsumerSecret() {
        return getCredential().consumerSecret();
    }

    public static String getToken() {
        return getCredential().token();
    }

    public static String getTokenSecret() {
        return getCredential().tokenSecret();
    }

    private static Credential getCredential() {
        if (instance == null) {
            File file = new File(Credential.class.getClassLoader().getResource(CREDS_CONFIG_FILE_NAME).getFile());

            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                instance = objectMapper.readValue(file, Credential.class);
            } catch (IOException e) {
                Assert.fail("Failed to get credentials from " + CREDS_CONFIG_FILE_NAME);
            }
        }

        return instance;
    }

    protected abstract String consumerKey();

    protected abstract String consumerSecret();

    protected abstract String token();

    protected abstract String tokenSecret();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {
        @JsonProperty("consumer_key")
        public abstract Builder consumerKey(String consumerKey);

        @JsonProperty("consumer_secret")
        public abstract Builder consumerSecret(String consumerSecret);

        @JsonProperty("token")
        public abstract Builder token(String token);

        @JsonProperty("token_secret")
        public abstract Builder tokenSecret(String tokenSecret);

        public abstract Credential build();
    }

    public static Builder builder() {
        return new AutoValue_Credential.Builder();
    }
}
