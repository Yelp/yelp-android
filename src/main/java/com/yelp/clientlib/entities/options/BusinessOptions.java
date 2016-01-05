package com.yelp.clientlib.entities.options;

import com.yelp.clientlib.entities.QueryOptions;

/**
 * Query options for Business Endpoints.
 *
 * @see <a href="https://www.yelp.com/developers/documentation/v2/business#lParam">https://www.yelp
 * .com/developers/documentation/v2/business#lParam</a>
 */
public class BusinessOptions extends QueryOptions {

    public final static String COUNTRY_CODE = "cc";
    public final static String LANGUAGE = "lang";
    public final static String LANGUAGE_FILTER = "lang_filter";
    public final static String ACTION_LINKS = "actionlinks";

    public String countryCode() {
        return getString(COUNTRY_CODE);
    }

    public String language() {
        return getString(LANGUAGE);
    }

    public Boolean languageFilter() {
        return getBoolean(LANGUAGE_FILTER);
    }

    public Boolean actionLinks() {
        return getBoolean(ACTION_LINKS);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String countryCode;
        private String language;
        private Boolean languageFilter;
        private Boolean actionLinks;

        Builder() {
        }

        Builder(BusinessOptions options) {
            this.countryCode = options.countryCode();
            this.language = options.language();
            this.languageFilter = options.languageFilter();
            this.actionLinks = options.actionLinks();
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder languageFilter(Boolean languageFilter) {
            this.languageFilter = languageFilter;
            return this;
        }

        public Builder actionLinks(Boolean actionLinks) {
            this.actionLinks = actionLinks;
            return this;
        }

        public BusinessOptions build() {
            BusinessOptions options = new BusinessOptions();
            options.set(COUNTRY_CODE, countryCode);
            options.set(LANGUAGE, language);
            options.set(LANGUAGE_FILTER, languageFilter);
            options.set(ACTION_LINKS, actionLinks);

            return options;
        }
    }
}