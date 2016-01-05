package com.yelp.clientlib.entities.options;

import org.junit.Assert;
import org.junit.Test;

public class BusinessOptionsTest {

    String countryCode = "US";
    String language = "en";
    Boolean languageFilter = true;
    Boolean actionLinks = true;

    @Test
    public void testBuilder() {
        BusinessOptions options = new BusinessOptions.Builder()
                .countryCode(countryCode)
                .actionLinks(actionLinks)
                .language(language)
                .languageFilter(languageFilter)
                .build();

        Assert.assertEquals(countryCode, options.countryCode());
        Assert.assertEquals(language, options.language());
        Assert.assertEquals(languageFilter, options.languageFilter());
        Assert.assertEquals(actionLinks, options.actionLinks());
    }

    @Test
    public void testOptionsToBuilder() {
        BusinessOptions options = new BusinessOptions.Builder()
                .countryCode(countryCode)
                .actionLinks(actionLinks)
                .language(language)
                .languageFilter(languageFilter)
                .build();

        BusinessOptions builtOptions = new BusinessOptions.Builder(options).build();

        Assert.assertEquals(options.countryCode(), builtOptions.countryCode());
        Assert.assertEquals(options.language(), builtOptions.language());
        Assert.assertEquals(options.languageFilter(), builtOptions.languageFilter());
        Assert.assertEquals(options.actionLinks(), builtOptions.actionLinks());
    }

    @Test
    public void testGetUnsetParams() {
        BusinessOptions options = new BusinessOptions();
        Assert.assertNull(options.countryCode());
        Assert.assertNull(options.language());
        Assert.assertNull(options.languageFilter());
        Assert.assertNull(options.actionLinks());
    }
}
