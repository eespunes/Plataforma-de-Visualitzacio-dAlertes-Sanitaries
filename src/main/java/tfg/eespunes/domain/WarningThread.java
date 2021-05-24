package tfg.eespunes.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tfg.eespunes.persistance.DatabaseController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class WarningThread extends Thread {
    private Warning warning;
    private DatabaseController databaseController;
    private final boolean isGreater;

    public WarningThread(Warning warning, DatabaseController databaseController) {
        this.warning = warning;
        this.databaseController = databaseController;
        isGreater = this.warning.getGreenValue() < this.warning.getYellowValue();
    }

    public void run() {
        while (true) {
//            float newLastValue = GetLastValueFromAPI();
//            if (knowAlertColor(newLastValue) > knowAlertColor(this.warning.getLastValue())) {
////                SendNotification(
//            }
//            this.warning.setLastValue(newLastValue);
//            this.databaseController.setWarningLastValue(this.warning.getId(),newLastValue);

            try {
                Thread.sleep(this.warning.getRefreshRate() * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private float GetLastValueFromAPI() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = this.warning.getRole().getHealthcareInstitution().getUsername() + ":" + this.warning.getRole().getHealthcareInstitution().getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        httpHeaders.add(HttpHeaders.AUTHORIZATION, authHeader);

        HttpEntity<String> request = new HttpEntity<String>("", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.warning.getRole().getHealthcareInstitution().getUrl() + this.warning.getUri(), HttpMethod.GET, request, String.class);


        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualObj = mapper.readTree(response.getBody());
            return (float) actualObj.get("value").asDouble();
        } catch (IOException e) {
        }
        return 0;
    }

    private int knowAlertColor(float lastValue) {
        if (isGreater) {
            if (lastValue < this.warning.getGreenValue())
                return 0;
            else if (lastValue < this.warning.getRedValue())
                return 1;
            else {
                return 2;
            }
        } else {
            if (lastValue > this.warning.getGreenValue())
                return 0;
            else if (lastValue > this.warning.getRedValue())
                return 1;
            else {
                return 2;
            }
        }
    }

    public Warning getWarning() {
        return warning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarningThread that = (WarningThread) o;
        return warning.getId() == that.warning.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(warning);
    }
}
