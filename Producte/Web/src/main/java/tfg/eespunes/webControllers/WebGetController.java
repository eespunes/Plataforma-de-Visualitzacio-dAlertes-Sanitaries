package tfg.eespunes.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;
import tfg.eespunes.persistance.DatabaseController;

@Controller
public class WebGetController {
    private DatabaseController databaseController;

    public WebGetController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/institution/create")
    public String createInstitution(Model model) {
        model.addAttribute("createHealthcareInstitution", new HealthcareInstitution());
        model.addAttribute("countries", databaseController.getAllCountries());
        return "healthcareInstitution/createHealthcareInstitution";
    }

    @GetMapping("/role/create")
    public String createRole(Model model) {
        model.addAttribute("createRole", new Role());
        model.addAttribute("healthcareInstitutions", databaseController.getAllHealthcareInstitutions());
        return "role/createRole";
    }
}
