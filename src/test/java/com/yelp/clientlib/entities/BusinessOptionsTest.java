package com.yelp.clientlib.entities;

import org.junit.Assert;
import org.junit.Test;

public class BusinessOptionsTest {

    @Test
    public void testSetAndGetParams() {
        String countryCode = "US";
        String language = "en";
        Boolean languageFilter = true;
        Boolean actionLinks = true;

        BusinessOptions options = new BusinessOptions();
        options.setCountryCode(countryCode);
        options.setLanguage(language);
        options.setLanguageFilter(languageFilter);
        options.setActionLinks(actionLinks);

        Assert.assertEquals(countryCode, options.getCountryCode());
        Assert.assertEquals(language, options.getLanguage());
        Assert.assertEquals(languageFilter, options.getLanguageFilter());
        Assert.assertEquals(actionLinks, options.getActionLinks());
    }

    @Test
    public void testGetUnsetParams() {
        BusinessOptions options = new BusinessOptions();
        Assert.assertNull(options.getCountryCode());
        Assert.assertNull(options.getLanguage());
        Assert.assertFalse(options.getLanguageFilter());
        Assert.assertFalse(options.getActionLinks());
    }
}
