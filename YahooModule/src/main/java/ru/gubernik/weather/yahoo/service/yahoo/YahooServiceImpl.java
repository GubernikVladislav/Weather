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

        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        // Make sure value is encoded
        try {
            parameters.add("location=" + URLEncoder.encode(location, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding error", e);
        }
        parameters.add("format=json");
        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString = null;
        try {
            signatureString = "GET&" +
                    URLEncoder.encode(url, "UTF-8") + "&" +
                    URLEncoder.encode(parametersList.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding error", e);
        }

        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + consumerKey + "\", " +
                "oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationLine);
        headers.set("Yahoo-App-Id", appId);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url + "?location=" + location + "&format=json",
                        HttpMethod.GET,
                        entity,
                        String.class);
        return responseEntity.getBody();
    }
}
