package com.yelp.clientlib.entities;

import java.util.HashMap;

public class BusinessOptions extends HashMap<String, String> {

    public final static String COUNTRY_CODE = "cc";
    public final static String LANGUAGE = "lang";
    public final static String LANGUAGE_FILTER = "lang_filter";
    public final static String ACTION_LINKS = "actionlinks";

    public void setCountryCode(String countryCode) {
        this.put(COUNTRY_CODE, countryCode);
    }

    public void setLanguage(String language) {
        this.put(LANGUAGE, language);
    }

    public void setLanguageFilter(Boolean useLanguageFilter) {
        this.put(LANGUAGE_FILTER, useLanguageFilter ? "true" : "false");
    }

    public void setActionLinks(Boolean includeActionLinks) {
        this.put(ACTION_LINKS, includeActionLinks ? "true" : "false");
    }

    public String getCountryCode() {
        return this.get(COUNTRY_CODE);
    }

    public String getLanguage() {
        return this.get(LANGUAGE);
    }

    public Boolean getLanguageFilter() {
        return Boolean.valueOf(this.get(LANGUAGE_FILTER));
    }

    public Boolean getActionLinks() {
        return Boolean.valueOf(this.get(ACTION_LINKS));
    }
}
