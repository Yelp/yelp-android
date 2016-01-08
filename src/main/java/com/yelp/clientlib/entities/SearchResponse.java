package com.yelp.clientlib.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_SearchResponse.Builder.class)
public abstract class SearchResponse implements Serializable {

    public abstract ArrayList<Business> businesses();

    @Nullable
    public abstract Region region();

    public abstract Integer total();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
    public abstract static class Builder {

        public abstract Builder businesses(ArrayList<Business> businesses);

        @Nullable
        public abstract Builder region(Region region);

        public abstract Builder total(Integer total);

        public abstract SearchResponse build();
    }

    public static Builder builder() {
        return new AutoValue_SearchResponse.Builder();
    }
}
