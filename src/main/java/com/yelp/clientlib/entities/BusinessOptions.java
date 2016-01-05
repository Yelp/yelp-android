package com.yelp.clientlib.entities;

/**
 * Query options for Business Endpoints.
 *
 * @see <a href="https://www.yelp.com/developers/documentation/v2/business#lParam">https://www.yelp
 * .com/developers/documentation/v2/business#lParam</a>
 */
public class BusinessOptions extends QueryParams {

    public final static String COUNTRY_CODE = "cc";
    public final static String LANGUAGE = "lang";
    public final static String LANGUAGE_FILTER = "lang_filter";
    public final static String ACTION_LINKS = "actionlinks";

    public void setCountryCode(String countryCode) {
        set(COUNTRY_CODE, countryCode);
    }

    public void setLanguage(String language) {
        set(LANGUAGE, language);
    }

    public void setLanguageFilter(Boolean languageFilter) {
        set(LANGUAGE_FILTER, languageFilter);
    }

    public void setActionLinks(Boolean actionLinks) {
        set(ACTION_LINKS, actionLinks);
    }

    public String getCountryCode() {
        return getString(COUNTRY_CODE);
    }

    public String getLanguage() {
        return getString(LANGUAGE);
    }

    public Boolean getLanguageFilter() {
        return getBoolean(LANGUAGE_FILTER);
    }

    public Boolean getActionLinks() {
        return getBoolean(ACTION_LINKS);
    }
}