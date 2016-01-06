package com.yelp.clientlib.entities.options;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SearchCoordinate {

    public abstract Double latitude();

    public abstract Double longitude();

    @Override
    public String toString() {
        return latitude() + "," + longitude();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder latitude(Double latitude);

        public abstract Builder longitude(Double longitude);

        public abstract SearchCoordinate build();
    }

    public static Builder builder() {
        return new AutoValue_SearchCoordinate.Builder();
    }
}