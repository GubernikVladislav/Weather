package ru.gubernik.weather.yahoo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gubernik.weather.model.Weather;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * {@inheritDoc}
 */
@Service
public class YahooServiceImpl implements YahooService {

    private final String appId = "kcPacc6g";
    private final String consumerKey = "dj0yJmk9aWZya3JHczVPYUR6JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTQz";
    private final String consumerSecret = "ec2cfce8afc04073b364580782043dd6d626bab3";

    private final String URL = "https://weather-ydn-yql.media.yahoo.com/forecastrss";
    private final String FORMAT = "format=json";

    /**
     * {@inheritDoc}
     */
    public void request(String location) throws UnsupportedEncodingException {
        long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key" + consumerKey);
        parameters.add("oauth_nonce" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp" + timestamp);
        parameters.add("oauth_version=1.0");
        parameters.add("location=" + URLEncoder.encode(location, "UTF-8"));
        parameters.add(FORMAT);
        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();
        for(int i = 0; i < parameters.size(); i++){
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString = "GET&" +
                URLEncoder.encode(URL, "UTF-8") + "&" +
                URLEncoder.encode(parametersList.toString(), "UTF-8");

        String signature = null;
        try{
            SecretKeySpec singinKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(singinKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println();
            System.exit(0);
        }

        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + consumerKey + "\", " +
                "oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationLine);
        headers.add("Yahoo-App-Id", appId);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Weather> response = restTemplate.exchange(URL + "?location=" + location + "&format=json", HttpMethod.GET, httpEntity, Weather.class);
    }

}
