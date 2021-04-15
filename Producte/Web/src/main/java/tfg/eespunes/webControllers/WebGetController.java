package tfg.eespunes.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;
import tfg.eespunes.domain.Warning;
import tfg.eespunes.persistance.DatabaseController;

import javax.validation.Valid;

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

    //CREAR
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

    @GetMapping("/user/create")
    public String createUser(Model model) {
        model.addAttribute("createEmployee", new Employee());
        model.addAttribute("roles", databaseController.getAllRoles());
        return "employee/createEmployee";
    }

    @GetMapping("/warning/create")
    public String createWarning(Model model) {
        model.addAttribute("createWarning", new Warning());
        model.addAttribute("roles", databaseController.getAllRoles());
        return "warning/createWarning";
    }

    //GET ALL
    @GetMapping("/institution/all")
    public String getAllInstitutions(Model model) {
        model.addAttribute("healthcareInstitutions", databaseController.getAllHealthcareInstitutions());
        return "healthcareInstitution/showHealthcareInstitutions";
    }

    @GetMapping("/role/all")
    public String getAllRoles(Model model) {
        model.addAttribute("roles", databaseController.getAllRoles());
        return "role/showRoles";
    }

    @GetMapping("/user/all")
    public String getAllEployeess(Model model) {
        model.addAttribute("employees", databaseController.getAllEmployees());
        return "employee/showEmployees";
    }

    @GetMapping("/warning/all")
    public String getAllWarningss(Model model) {
        model.addAttribute("warnings", databaseController.getAllWarnings());
        return "warning/showWarnings";
    }
}