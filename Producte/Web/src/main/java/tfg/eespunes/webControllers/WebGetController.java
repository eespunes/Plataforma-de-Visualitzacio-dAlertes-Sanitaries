package tfg.eespunes.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;
import tfg.eespunes.domain.Warning;
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
    public String getAllEployees(Model model) {
        model.addAttribute("employees", databaseController.getAllEmployees());
        return "employee/showEmployees";
    }

    @GetMapping("/warning/all")
    public String getAllWarnings(Model model) {
        model.addAttribute("warnings", databaseController.getAllWarnings());
        return "warning/showWarnings";
    }

    //GET ONE
    @GetMapping("/institution/{healthcareInstitutionID}/{countryID}")
    public String getInstitution(@PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        model.addAttribute("healthcareInstitution", databaseController.getHealthcareInstitution(healthcareInstitutionID, countryID));
        model.addAttribute("roles", databaseController.getAllRolesOfHealthcareInstitution(healthcareInstitutionID, countryID));
        return "healthcareInstitution/showHealthcareInstitution";
    }

    @GetMapping("/role/{roleName}/{healthcareInstitutionID}/{countryID}")
    public String getRole(@PathVariable String roleName, @PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        model.addAttribute("role", databaseController.getRole(roleName, healthcareInstitutionID, countryID));
        model.addAttribute("healthcareInstitution", databaseController.getHealthcareInstitution(healthcareInstitutionID, countryID));
        model.addAttribute("warnings", databaseController.getAllWarningsOfRole(roleName, healthcareInstitutionID, countryID));
        return "role/showRole";
    }

    @GetMapping("/user/{id}")
    public String getEployee(@PathVariable int id, Model model) {
        model.addAttribute("employee", databaseController.getEmployee(id));
        return "employee/showEmployee";
    }

    @GetMapping("/warning/{id}")
    public String getWarning(@PathVariable int id, Model model) {
        model.addAttribute("warning", databaseController.getWarning(id));
        return "warning/showWarning";
    }
}