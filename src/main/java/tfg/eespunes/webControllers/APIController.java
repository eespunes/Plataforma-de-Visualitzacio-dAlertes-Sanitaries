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
import tfg.eespunes.domain.threads.NotificationMonitor;
import tfg.eespunes.domain.threads.WarningThread;
import tfg.eespunes.persistance.DatabaseController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class APIController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private DatabaseController databaseController;
    private List<WarningThread> threads;
    private NotificationMonitor notificationMonitor;

    public APIController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        notificationMonitor = new NotificationMonitor();
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
        String[] splitted = notificationToken.split("_");
        if (splitted.length == 2) {
            notificationToken = splitted[0] + "[" + splitted[1] + "]";
        }

        if (!employee.getNotificationToken().equals(notificationToken)) {
            if (!PushClient.isExponentPushToken(notificationToken)) {
                return null;
            }
            databaseController.updateNotificationToken(username, notificationToken);
            employee.setNotificationToken(notificationToken);
        }

        startThreads();

        if (passwordEncoder.matches(password, employee.getPassword())) {
            return employee;
        } else {
            return null;
        }
    }

    @GetMapping("/logout/{username}/{notificationToken}")
    public boolean logout(@PathVariable String username, @PathVariable String notificationToken) {

        Employee employee = databaseController.getEmployee(username);
        databaseController.updateNotificationToken(username, "");
        employee.setNotificationToken(notificationToken);

        return true;
    }

    public void startThreads() {
        List<Warning> warnings = databaseController.getAllWarnings();
        for (Warning warning : warnings) {
            WarningThread thread=warningIsInThread(warning.getId());
            if (thread==null) {
                WarningThread newWarning = new WarningThread(warning, databaseController, notificationMonitor);
                newWarning.start();
                threads.add(newWarning);
            }else{
                thread.setWarning(warning);
            }
        }
    }

    private WarningThread warningIsInThread(int id) {
        for (WarningThread thread : threads) {
            if (thread.getWarning().getId() == id)
                return thread;
        }
        return null;
    }
}
