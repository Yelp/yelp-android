package com.yelp.clientlib.entities.options;

import com.yelp.clientlib.entities.QueryOptions;

public class SearchOptions extends QueryOptions {

    public final static String TERM = "term";
    public final static String LIMIT = "limit";
    public final static String OFFSET = "offset";
    public final static String SORT = "sort";
    public final static String CATEGORY_FILTER = "category_filter";
    public final static String RADIUS_FILTER = "radius_filter";
    public final static String DEALS_FILTER = "deals_filter";
    public final static String LOCATION = "location";
    public final static String COUNTRY_CODE = "cc";
    public final static String LANGUAGE = "lang";
    public final static String ACTION_LINKS = "actionlinks";

    public void setTerm(String term) {
        set(TERM, term);
    }

    public void setLimit(Integer limit) {
        set(LIMIT, limit);
    }

    public void setOffset(Integer offset) {
        set(OFFSET, offset);
    }

    public void setSort(Integer sort) {
        set(SORT, sort);
    }

    public void setCategoryFilter(String categoryFilter) {
        set(CATEGORY_FILTER, categoryFilter);
    }

    public void setRadiusFilter(Integer radiusFilter) {
        set(RADIUS_FILTER, radiusFilter);
    }

    public void setDealsFilter(Boolean dealsFilter) {
        set(DEALS_FILTER, dealsFilter);
    }

    public void setLocation(String location) {
        set(LOCATION, location);
    }

    public void setCountryCode(String countryCode) {
        set(COUNTRY_CODE, countryCode);
    }

    public void setLanguage(String language) {
        set(LANGUAGE, language);
    }

    public void setActionLinks(Boolean actionLinks) {
        set(ACTION_LINKS, actionLinks);
    }

    public String getTerm() {
        return getString(TERM);
    }

    public Integer getLimit() {
        return getInteger(LIMIT);
    }

    public Integer getOffset() {
        return getInteger(OFFSET);
    }

    public Integer getSort() {
        return getInteger(SORT);
    }

    public String getCategoryFilter() {
        return getString(CATEGORY_FILTER);
    }

    public Integer getRadiusFilter() {
        return getInteger(RADIUS_FILTER);
    }

    public Boolean getDealsFilter() {
        return getBoolean(DEALS_FILTER);
    }

    public String getLocation() {
        return getString(LOCATION);
    }

    public String getCountryCode() {
        return getString(COUNTRY_CODE);
    }

    public String getLanguage() {
        return getString(LANGUAGE);
    }

    public Boolean getActionLinks() {
        return getBoolean(ACTION_LINKS);
    }
}