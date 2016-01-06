package com.yelp.clientlib.entities.options;

import com.google.auto.value.AutoValue;
import com.yelp.clientlib.annotation.Nullable;

@AutoValue
public abstract class SearchLocation {

    public abstract Double latitude();

    public abstract Double longitude();

    @Nullable
    public abstract Double accuracy();

    @Nullable
    public abstract Double altitude();

    @Nullable
    public abstract Double altitudeAccuracy();

    @Override
    public String toString() {
        String coordinate = latitude() + "," + longitude();
        Double[] fields = new Double[]{accuracy(), altitude(), altitudeAccuracy()};

        for (Double field : fields) {
            if (field == null) {
                return coordinate;
            } else {
                coordinate = coordinate + "," + field;
            }
        }
        return coordinate;
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder latitude(Double latitude);

        public abstract Builder longitude(Double longitude);

        public abstract Builder accuracy(Double latitude);

        public abstract Builder altitude(Double altitude);

        public abstract Builder altitudeAccuracy(Double altitudeAccuracy);

        public abstract SearchLocation build();
    }

    public static Builder builder() {
        return new AutoValue_SearchLocation.Builder();
    }
}
