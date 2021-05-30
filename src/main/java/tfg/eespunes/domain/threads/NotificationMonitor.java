package tfg.eespunes.domain.threads;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.PushClient;
import io.github.jav.exposerversdk.PushClientException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.Warning;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotificationMonitor {

    private final int GREEN = 0;
    private final int YELLOW = 1;
    private final int RED = 2;

    private Lock lock = new ReentrantLock();
    ObjectMapper oMapper = new ObjectMapper();

    float getLastValueFromAPI(Warning warning) {
        lock.lock();
        float value;

        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = warning.getRole().getHealthcareInstitution().getUsername() + ":" + warning.getRole().getHealthcareInstitution().getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        httpHeaders.add(HttpHeaders.AUTHORIZATION, authHeader);

        HttpEntity<String> request = new HttpEntity<String>("", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(warning.getRole().getHealthcareInstitution().getUrl() + warning.getUri(), HttpMethod.GET, request, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(response.getBody());
            value = (float) actualObj.get("value").asDouble();
        } catch (Exception e) {
            value = 0;
        }

        lock.unlock();

        return value;
    }

    void sendNotifications(List<Employee> employees, int newWarningColor, Warning warning) throws PushClientException {
        String title = "";
        switch (newWarningColor) {
            case GREEN:
                title = "ALERTA VERDA!";
                break;
            case YELLOW:
                title = "ALERTA GROGA!";
                break;
            case RED:
                title = "ALERTA VERMELLA!";
                break;
        }

        String message = warning.getNotificationMessage();

        List<ExpoPushMessage> notificationMessages = new ArrayList<>();
        for (Employee employee : employees) {
            if (PushClient.isExponentPushToken(employee.getNotificationToken())) {
                ExpoPushMessage expoPushMessage = new ExpoPushMessage();
                expoPushMessage.getTo().add(employee.getNotificationToken());
                expoPushMessage.setTitle(title);
                expoPushMessage.setBody(message);
                expoPushMessage.setData(oMapper.convertValue(warning, Map.class));
                notificationMessages.add(expoPushMessage);
            }
        }
        PushClient client = new PushClient();

        List<List<ExpoPushMessage>> chunks = client.chunkPushNotifications(notificationMessages);
        for (List<ExpoPushMessage> chunk : chunks) {
            client.sendPushNotificationsAsync(chunk);
        }
    }

    public int getGREEN() {
        return GREEN;
    }

    public int getYELLOW() {
        return YELLOW;
    }

    public int getRED() {
        return RED;
    }
}