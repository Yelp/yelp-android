package com.yelp.clientlib.entities.options;

import org.junit.Assert;
import org.junit.Test;

public class SearchOptionsTest {

    @Test
    public void testSetAndGetParams() {
        String term = "sushi";
        Integer limit = 3;
        Integer offset = 1;
        Integer sort = 0;
        String category_filter = "restaurants";
        Integer radius_filter = 100;
        Boolean deals_filter = true;
        String countryCode = "US";
        String language = "en";
        Boolean actionLinks = true;

        SearchOptions options = new SearchOptions();

        options.setTerm(term);
        options.setLimit(limit);
        options.setOffset(offset);
        options.setSort(sort);
        options.setCategoryFilter(category_filter);
        options.setRadiusFilter(radius_filter);
        options.setDealsFilter(deals_filter);
        options.setCountryCode(countryCode);
        options.setLanguage(language);
        options.setActionLinks(actionLinks);

        Assert.assertEquals(term, options.getTerm());
        Assert.assertEquals(limit, options.getLimit());
        Assert.assertEquals(offset, options.getOffset());
        Assert.assertEquals(sort, options.getSort());
        Assert.assertEquals(category_filter, options.getCategoryFilter());
        Assert.assertEquals(radius_filter, options.getRadiusFilter());
        Assert.assertEquals(deals_filter, options.getDealsFilter());
        Assert.assertEquals(countryCode, options.getCountryCode());
        Assert.assertEquals(language, options.getLanguage());
        Assert.assertEquals(actionLinks, options.getActionLinks());
    }

    @Test
    public void testGetUnsetParams() {
        SearchOptions options = new SearchOptions();

        Assert.assertNull(options.getTerm());
        Assert.assertNull(options.getLimit());
        Assert.assertNull(options.getOffset());
        Assert.assertNull(options.getSort());
        Assert.assertNull(options.getCategoryFilter());
        Assert.assertNull(options.getRadiusFilter());
        Assert.assertNull(options.getDealsFilter());
        Assert.assertNull(options.getCountryCode());
        Assert.assertNull(options.getLanguage());
        Assert.assertNull(options.getActionLinks());
    }
}