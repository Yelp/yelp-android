package com.yelp.clientlib.connection;

import com.squareup.okhttp.Request;

import org.scribe.model.ParameterList;
import org.scribe.services.HMACSha1SignatureService;
import org.scribe.utils.OAuthEncoder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;


/**
 * Construct signatures for requests by using HMAC-SHA1. This class follows
 * <a href="https://tools.ietf.org/html/rfc5849#section-3.4">RFC5849-Section-3.4</a> to sign the request.
 */
public class HmacSha1Signer {
    private static final String SIGNATURE_BASE_STRING_FORMAT = "%s&%s&%s";
    private static final String BASE_STRING_URI_FORMAT = "%s://%s%s";

    private final String consumerSecret;
    private final String tokenSecret;
    private final HMACSha1SignatureService signatureService;

    public HmacSha1Signer(String consumerSecret, String tokenSecret) {
        this.consumerSecret = consumerSecret;
        this.tokenSecret = tokenSecret;
        this.signatureService = new HMACSha1SignatureService();
    }

    /**
     * Construct the signature for the request by following
     * <a href="https://tools.ietf.org/html/rfc5849#section-3.4">RFC5849-Section-3.4</a>.
     */
    public String sign(Request request, Map<String, String> requestParams) throws IOException {
        try {
            String signatureBaseString = getSignatureBaseString(request, requestParams);
            return signatureService.getSignature(signatureBaseString, consumerSecret, tokenSecret);
        } catch (IOException e) {
            // TODO: Add error handling to throw specific error.
            throw e;
        }
    }

    /**
     * Construct the signature base string for the request by following
     * <a href="https://tools.ietf.org/html/rfc5849#section-3.4.1">RFC5489-Section-3.4.1</a>.
     */
    private String getSignatureBaseString(Request request, Map<String, String> requestParams) throws IOException {
        ParameterList parameterList = new ParameterList();
        for (Map.Entry<String, String> requestParamEntry : requestParams.entrySet()) {
            parameterList.add(requestParamEntry.getKey(), requestParamEntry.getValue());
        }

        String method = request.method();
        String baseUrl = OAuthEncoder.encode(getBaseStringUri(request.uri()));
        String params = parameterList.sort().asOauthBaseString();
        return String.format(SIGNATURE_BASE_STRING_FORMAT, method, baseUrl, params);
    }

    /**
     * Construct the base string URI by following
     * <a href="https://tools.ietf.org/html/rfc5849#section-3.4.1.2">RFC5489-Section-3.4.1.2</a>.
     */
    private String getBaseStringUri(URI uri) {
        return String.format(BASE_STRING_URI_FORMAT, uri.getScheme(), uri.getAuthority(), uri.getRawPath());
    }
}
