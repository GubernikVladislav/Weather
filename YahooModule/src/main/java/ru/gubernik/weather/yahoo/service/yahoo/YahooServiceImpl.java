package ru.gubernik.weather.yahoo.service.yahoo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class YahooServiceImpl implements YahooService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String createYahooRequest(String location)  {

        if (location == null || location.isEmpty()){
            return "";
        }

        RestTemplate restTemplate = new RestTemplate();

        final String appId = "kcPacc6g";
        final String consumerKey = "dj0yJmk9aWZya3JHczVPYUR6JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTQz";
        final String consumerSecret = "ec2cfce8afc04073b364580782043dd6d626bab3";
        final String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

        long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        List<String> parameters = createParameters(consumerKey, timestamp, oauthNonce, location);
        StringBuilder parametersList = appendSeparators(parameters);
        String signature = createSignature(consumerSecret, parametersList, url);
        String authorizationLine = createAuthorizationLine(consumerKey, timestamp, oauthNonce, signature);

        HttpEntity<?> entity = createHeaders(appId, authorizationLine);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url + "?location=" + location +"&u=c&format=json",
                        HttpMethod.GET,
                        entity,
                        String.class);

        return responseEntity.getBody();
    }

    private HttpEntity<?> createHeaders(String appId, String authorizationLine) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationLine);
        headers.set("Yahoo-App-Id", appId);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private String createSignature(String consumerSecret, StringBuilder parametersList, String url) {

        String signatureString;
        try {
            signatureString = "GET&" +
                    URLEncoder.encode(url, "UTF-8") + "&" +
                    URLEncoder.encode(parametersList.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding error", e);
        }

        String signature;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            throw new RuntimeException("Encoding error", e);
        }
        return signature;
    }

    private String createAuthorizationLine(String consumerKey, long timestamp, String oauthNonce, String signature) {
        return "OAuth " +
                    "oauth_consumer_key=\"" + consumerKey + "\", " +
                    "oauth_nonce=\"" + oauthNonce + "\", " +
                    "oauth_timestamp=\"" + timestamp + "\", " +
                    "oauth_signature_method=\"HMAC-SHA1\", " +
                    "oauth_signature=\"" + signature + "\", " +
                    "oauth_version=\"1.0\"";
    }

    private StringBuilder appendSeparators(List<String> parameters) {
        StringBuilder parametersList = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append((i > 0) ? "&" : "").append(parameters.get(i));
        }
        return parametersList;
    }

    private List<String> createParameters(String consumerKey, long timestamp, String oauthNonce, String location) {

        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        try {
            parameters.add("location=" + URLEncoder.encode(location, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding error", e);
        }
        parameters.add("format=json");
        parameters.add("u=c");
        Collections.sort(parameters);
        return parameters;
    }

}
