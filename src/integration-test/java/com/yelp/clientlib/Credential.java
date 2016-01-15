package com.yelp.clientlib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Util class to get test credentials from credentials.yaml file. <br />
 * JSON annotations are used by Jackson {@link ObjectMapper} to determine field name mappings. To consume YAML files,
 * {@link YAMLFactory} is passed into {@link ObjectMapper} to parse YAML formatted data. Refer to
 * <a href="https://github.com/FasterXML/jackson-dataformat-yamlr">jackson-dataformat-yaml</a> for more information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Credential.Builder.class)
public class Credential {

    private static final String CREDS_CONFIG_FILE_NAME = "credentials.yaml";

    private static Credential instance;

    private String consumerKey;
    private String consumerSecret;
    private String token;
    private String tokenSecret;

    private Credential(Builder builder) {
        consumerKey = builder.consumerKey;
        consumerSecret = builder.consumerSecret;
        token = builder.token;
        tokenSecret = builder.tokenSecret;
    }

    public static String consumerKey() {
        return getCredential().consumerKey;
    }

    public static String consumerSecret() {
        return getCredential().consumerSecret;
    }

    public static String token() {
        return getCredential().token;
    }

    public static String tokenSecret() {
        return getCredential().tokenSecret;
    }

    private static Credential getCredential() {
        if (instance == null) {
            InputStream inputStream = Credential.class.getClassLoader().getResourceAsStream(CREDS_CONFIG_FILE_NAME);
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                instance = objectMapper.readValue(inputStream, Credential.class);
            } catch (IOException e) {
                Assert.fail("Failed to get credentials from " + CREDS_CONFIG_FILE_NAME + ": " + e.toString());
            }
        }

        return instance;
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public static final class Builder {
        private String consumerKey;
        private String consumerSecret;
        private String token;
        private String tokenSecret;

        public Builder() {
        }

        @JsonProperty("consumer_key")
        public Builder consumerKey(String val) {
            consumerKey = val;
            return this;
        }

        @JsonProperty("consumer_secret")
        public Builder consumerSecret(String val) {
            consumerSecret = val;
            return this;
        }

        @JsonProperty("token")
        public Builder token(String val) {
            token = val;
            return this;
        }

        @JsonProperty("token_secret")
        public Builder tokenSecret(String val) {
            tokenSecret = val;
            return this;
        }

        public Credential build() {
            return new Credential(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}