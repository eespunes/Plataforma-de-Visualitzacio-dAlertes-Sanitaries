package tfg.eespunes.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ThreadCommon {
    private final WarningThread warningThread;

    public ThreadCommon(WarningThread warningThread) {
        this.warningThread = warningThread;
    }

    float GetLastValueFromAPI() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = warningThread.getWarning().getRole().getHealthcareInstitution().getUsername() + ":" + warningThread.getWarning().getRole().getHealthcareInstitution().getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        httpHeaders.add(HttpHeaders.AUTHORIZATION, authHeader);

        HttpEntity<String> request = new HttpEntity<String>("", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(warningThread.getWarning().getRole().getHealthcareInstitution().getUrl() + warningThread.getWarning().getUri(), HttpMethod.GET, request, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(response.getBody());
            return (float) actualObj.get("value").asDouble();
        } catch (IOException e) {
        }
        return 0;
    }
}