package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import org.scribe.model.ParameterList;
import org.scribe.services.HMACSha1SignatureService;
import org.scribe.utils.OAuthEncoder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;


/**
 * Sign requests by using HMAC-SHA1.
 */
public class HmacSha1Signer {
    private static final String SIGNATURE_BASE_STRING_FORMAT = "%s&%s&%s";
    private static final String BASE_URL_STRING_FORMAT = "%s://%s%s";

    private final String consumerSecret;
    private final String tokenSecret;
    private final HMACSha1SignatureService signatureService;

    public HmacSha1Signer(String consumerSecret, String tokenSecret) {
        this.consumerSecret = consumerSecret;
        this.tokenSecret = tokenSecret;
        this.signatureService = new HMACSha1SignatureService();
    }

    public String sign(Request request, Map<String, String> requestParams) throws IOException {
        try {
            String signatureBaseString = getSignatureBaseString(request, requestParams);
            return signatureService.getSignature(signatureBaseString, consumerSecret, tokenSecret);
        } catch (IOException e) {
            // TODO: Add error handling to throw specific error.
            throw e;
        }
    }

    private String getSignatureBaseString(Request request, Map<String, String> requestParams) throws IOException {
        ParameterList parameterList = new ParameterList();
        for (Map.Entry<String, String> requestParamEntry : requestParams.entrySet()) {
            parameterList.add(requestParamEntry.getKey(), requestParamEntry.getValue());
        }

        String method = request.method();
        String baseUrl = OAuthEncoder.encode(getBaseUrlString(request.uri()));
        String params = parameterList.sort().asOauthBaseString();
        return String.format(SIGNATURE_BASE_STRING_FORMAT, method, baseUrl, params);
    }

    private String getBaseUrlString(URI uri) {
        return String.format(BASE_URL_STRING_FORMAT, uri.getScheme(), uri.getAuthority(), uri.getRawPath());
    }
}
