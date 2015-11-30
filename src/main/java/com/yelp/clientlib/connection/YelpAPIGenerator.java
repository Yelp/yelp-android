package com.yelp.clientlib.connection;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;


/**
 * Util class to create YelpAPI as the stub to use Yelp API. This is the entry point to use this clientlib.
 * <p>
 * Example:<br />
 * YelpAPI yelpAPI = YelpAPIGenerator.createAPIStub(consumerKey, consumerSecret, token, tokenSecret);<br />
 * Response<Business> response = yelpAPI.getBusiness(businessId).execute();
 * </p>
 */
public class YelpAPIGenerator {

    public static String YELP_API_BASE_URL = "https://api.yelp.com";

    public static YelpAPI createAPIStub(String consumerKey, String consumerSecret, String token, String tokenSecret) {

        Interceptor oAuthInterceptor = new OAuth10aInterceptor(consumerKey, consumerSecret, token, tokenSecret);
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(oAuthInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YELP_API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(YelpAPI.class);
    }
}

/**
 * {@link com.squareup.okhttp.Interceptor} which authorizes each out-going requests. It intercepts each
 * request and attaches an OAuth1.0a authorization header to the request.
 */
class OAuth10aInterceptor implements Interceptor {

    private String consumerKey;
    private String token;
    private HmacSha1Signer hmacSha1Signer;

    OAuth10aInterceptor(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.consumerKey = consumerKey;
        this.token = token;
        this.hmacSha1Signer = new HmacSha1Signer(consumerSecret, tokenSecret);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Map<String, String> oAuthParams = prepareOAuthParams();
        Map<String, String> requestParams = prepareRequestParams(originalRequest, oAuthParams);

        try {
            String signature = this.hmacSha1Signer.sign(originalRequest, requestParams);
            oAuthParams.put(OAuthConstants.OAUTH_SIGNATURE_NAME, signature);

        } catch (IOException e) {
            //TODO: Handle exception.
            return null;
        }

        Request authorizedRequest = originalRequest.newBuilder()
                .header(OAuthConstants.HTTP_AUTHORIZATION_HEADER, getAuthHeaderValue(oAuthParams))
                .build();

        return chain.proceed(authorizedRequest);
    }

    /**
     * Construct the OAuth parameters by following
     * <a href="https://tools.ietf.org/html/rfc5849#section-3.1">RFC5849-Section-3.1</a>.
     */
    private Map<String, String> prepareOAuthParams() {
        Map<String, String> oAuthParams = new HashMap<>();
        oAuthParams.put(OAuthConstants.OAUTH_CONSUMER_KEY_NAME, this.consumerKey);
        oAuthParams.put(OAuthConstants.OAUTH_NONCE_NAME, Long.toString(new Random(System.nanoTime()).nextLong()));
        oAuthParams.put(OAuthConstants.OAUTH_SIGNATURE_METHOD_NAME, OAuthConstants.OAUTH_SIGNATURE_METHOD_VALUE);
        oAuthParams.put(OAuthConstants.OAUTH_TIMESTAMP_NAME, Long.toString(System.currentTimeMillis() / 1000L));
        oAuthParams.put(OAuthConstants.OAUTH_TOKEN_NAME, this.token);
        return oAuthParams;
    }

    /**
     * Construct the request parameters to calculate the OAuth signature by following
     * <a href="https://tools.ietf.org/html/rfc5849#section-3.4.1.3">RFC5849-Section-3.4.1.3</a>.
     */
    private Map<String, String> prepareRequestParams(Request request, Map<String, String> oAuthParams) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.putAll(oAuthParams);

        HttpUrl requestHttpUrl = request.httpUrl();
        for (String queryParamName : requestHttpUrl.queryParameterNames()) {
            requestParams.put(queryParamName, requestHttpUrl.queryParameter(queryParamName));
        }

        return requestParams;
    }

    /**
     * Construct the OAuth header for the request by following
     * <a href="https://tools.ietf.org/html/rfc5849#section-3.1">RFC5849-Section-3.1</a>.
     */
    private String getAuthHeaderValue(Map<String, String> oAuthParams) {
        StringBuilder sb = new StringBuilder();
        sb.append("OAuth ");

        Iterator<Map.Entry<String, String>> iterator = oAuthParams.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(String.format("%s=\"%s\"", entry.getKey(), entry.getValue()));
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}