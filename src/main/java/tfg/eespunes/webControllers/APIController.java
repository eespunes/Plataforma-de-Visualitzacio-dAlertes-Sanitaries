package tfg.eespunes.webControllers;

import io.github.jav.exposerversdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.Warning;
import tfg.eespunes.domain.WarningThread;
import tfg.eespunes.persistance.DatabaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class APIController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private DatabaseController databaseController;
    private List<WarningThread> threads;

    public APIController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        threads = new ArrayList<WarningThread>();
    }

    @GetMapping("/warning/all/{roleName}/{healthcareInstitutionID}/{countryID}")
    public List<Warning> getAllWarnings(@PathVariable String roleName, @PathVariable int healthcareInstitutionID, @PathVariable String countryID) {
        List<Warning> allWarnings = databaseController.getAllWarningsOfRole(roleName, healthcareInstitutionID, countryID);
        return allWarnings;
    }

    @GetMapping("/warning/{id}")
    public Warning getWarning(@PathVariable int id) {
        return databaseController.getWarning(id);
    }

    @GetMapping("/warning/edit/{id}/{value}")
    public boolean setWarningLastValue(@PathVariable int id, @PathVariable float value) {
        databaseController.setWarningLastValue(id, value);
        return true;
    }

    @GetMapping("/employee/{username}")
    public Employee getEmployee(@PathVariable String username) {
        return databaseController.getEmployee(username);
    }

    @GetMapping("/employee/change/{username}/{newPassword}")
    public int changePassword(@PathVariable String username, @PathVariable String newPassword) {
        return databaseController.changePassword(username, newPassword);
    }

    @GetMapping("/login/{username}/{password}/{notificationToken}")
    public Employee login(@PathVariable String username, @PathVariable String password, @PathVariable String notificationToken) {

        Employee employee = databaseController.getEmployee(username);
        if (!PushClient.isExponentPushToken(notificationToken)) {
            if (passwordEncoder.matches(password, employee.getPassword())) {
                return employee;
            } else {
                return null;
            }
        }

        databaseController.updateNotificationToken(username, notificationToken);
        employee = databaseController.getEmployee(username);

        String title = "HELLO FROM SERVER!!";
        String message = "Hello " + employee.getUsername() + " with token " + employee.getNotificationToken() + "!";

        ExpoPushMessage expoPushMessage = new ExpoPushMessage();
        expoPushMessage.getTo().add(notificationToken);
        expoPushMessage.setTitle(title);
        expoPushMessage.setBody(message);

        List<ExpoPushMessage> expoPushMessages = new ArrayList<>();
        expoPushMessages.add(expoPushMessage);

        PushClient client = null;
        try {
            client = new PushClient();
        } catch (PushClientException e) {
            e.printStackTrace();
        }
        List<List<ExpoPushMessage>> chunks = client.chunkPushNotifications(expoPushMessages);

        List<CompletableFuture<List<ExpoPushTicket>>> messageRepliesFutures = new ArrayList<>();

        for (List<ExpoPushMessage> chunk : chunks) {
            messageRepliesFutures.add(client.sendPushNotificationsAsync(chunk));
        }

        if (passwordEncoder.matches(password, employee.getPassword())) {
            return employee;
        } else {
            return null;
        }
    }

    @GetMapping("/logout/{registrationToken}")
    public boolean logout(@PathVariable String registrationToken) {

//        List<Warning> warnings = databaseController.getAllWarnings();
//        for (Warning warning : warnings) {
//            FirebaseMessaging.getInstance().unsubscribeToTopic(registrationTokens, warning.getId());
//        }

        return true;
    }

    public void startThreads() {
        List<Warning> warnings = databaseController.getAllWarnings();
        for (Warning warning : warnings) {
            if (!warningIsInThread(warning.getId())) {
                WarningThread newWarning = new WarningThread(warning, databaseController);
                newWarning.start();
                threads.add(newWarning);
            }
        }
    }

    private boolean warningIsInThread(int id) {
        for (WarningThread thread : threads) {
            if (thread.getWarning().getId() == id)
                return true;
        }
        return false;
    }

    public void stopThreads() {
        List<Warning> warnings = databaseController.getAllWarnings();

        for (WarningThread thread : threads) {
            if (!threadIsInWarning(thread.getWarning().getId(), warnings)) {
                thread.stop();
                threads.remove(thread);
            }
        }
    }

    private boolean threadIsInWarning(int id, List<Warning> warnings) {
        for (Warning warning : warnings) {
            if (warning.getId() == id)
                return true;
        }
        return false;
    }
}
