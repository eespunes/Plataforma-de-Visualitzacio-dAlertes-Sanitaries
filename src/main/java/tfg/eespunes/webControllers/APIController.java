package tfg.eespunes.webControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.Warning;
import tfg.eespunes.persistance.DatabaseController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class APIController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private DatabaseController databaseController;

    public APIController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @GetMapping("/warning/all")
    public List<Warning> getAllWarnings() {
        List<Warning> allWarnings = databaseController.getAllWarnings();
        for (Warning warning : allWarnings) {
            System.out.println(warning.getName());
        }
        return allWarnings;
    }

    @GetMapping("/warning/{id}")
    public Warning getWarning(@PathVariable int id) {
        return databaseController.getWarning(id);
    }

    @GetMapping("/employee/{username}")
    public Employee getEmployee(@PathVariable String username) {
        return databaseController.getEmployee(username);
    }

    @GetMapping("/employee/{username}/{newPassword}")
    public int changePassword(@PathVariable String username, @PathVariable String newPassword) {
        return databaseController.changePassword(username, newPassword);
    }

    @GetMapping("/login/{username}/{password}/{registrationToken}")
    public boolean login(@PathVariable String username, @PathVariable String password, @PathVariable String registrationToken) {
        Employee employee = databaseController.getEmployee(username);

//        List<Warning> warnings = databaseController.getAllWarnings();
//        for (Warning warning : warnings) {
//            FirebaseMessaging.getInstance().unsubscribeToTopic(registrationTokens, warning.getId());
//        }
//        warnings = databaseController.getAllWarningsOfRole(employee.getRole().getName(), employee.getRole().getHealthInstitutionID(), employee.getRole().getCountryId());
//        for (Warning warning : warnings) {
//            FirebaseMessaging.getInstance().subscribeToTopic(registrationTokens, warning.getId());
//        }

        return passwordEncoder.matches(password, employee.getPassword());
    }

    @GetMapping("/logout/{registrationToken}")
    public boolean logout(@PathVariable String registrationToken) {

//        List<Warning> warnings = databaseController.getAllWarnings();
//        for (Warning warning : warnings) {
//            FirebaseMessaging.getInstance().unsubscribeToTopic(registrationTokens, warning.getId());
//        }

        return true;
    }
}
