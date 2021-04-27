package tfg.eespunes.webControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;
import tfg.eespunes.domain.Warning;
import tfg.eespunes.persistance.DatabaseController;

import javax.validation.Valid;

@Controller
public class WebPostController {
    private DatabaseController databaseController;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public WebPostController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @PostMapping("/institution/create")
    public String createInstitution(HealthcareInstitution healthcareInstitution, RedirectAttributes redirectAttributes) {
        healthcareInstitution.setCountry(databaseController.getCountry(healthcareInstitution.getTempCountry()));
        healthcareInstitution.setId(databaseController.addHealthcareInstitution(healthcareInstitution));

        redirectAttributes.addAttribute("healthcareInstitutionID", healthcareInstitution.getId());
        redirectAttributes.addAttribute("countryID", healthcareInstitution.getCountry().getId());
        return "redirect:/institution/{healthcareInstitutionID}/{countryID}";
    }

    @PostMapping("/role/create")
    public String createRole(Role role, RedirectAttributes redirectAttributes) {
        String[] healthcareInstitution = role.getTempHealthcareInstitution().split("-");
        role.setHealthcareInstitution(databaseController.getHealthcareInstitution(Integer.parseInt(healthcareInstitution[1]), healthcareInstitution[0]));
        databaseController.addRole(role);

        redirectAttributes.addAttribute("roleName", role.getName());
        redirectAttributes.addAttribute("healthcareInstitutionID", role.getHealthcareInstitution().getId());
        redirectAttributes.addAttribute("countryID", role.getHealthcareInstitution().getCountry().getId());
        return "redirect:/role/{roleName}/{healthcareInstitutionID}/{countryID}";
    }

    @PostMapping("/employee/create")
    public String createEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        String[] role = employee.getTempRoleName().split("-");
        employee.setRole(databaseController.getRole(role[2], Integer.parseInt(role[1]), role[0]));

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        databaseController.addEmployee(employee);

        redirectAttributes.addAttribute("username", employee.getUsername());
        return "redirect:/employee/{username}";
    }

    @PostMapping("/warning/create")
    public String createWarning(Warning warning, RedirectAttributes redirectAttributes) {
        String[] role = warning.getTempRoleName().split("-");
        warning.setRole(databaseController.getRole(role[2], Integer.parseInt(role[1]), role[0]));

        warning.setId(databaseController.addWarning(warning));

        redirectAttributes.addAttribute("id", warning.getId());
        return "redirect:/warning/{id}";
    }

    //EDIT
    @PostMapping("/institution/edit/{healthcareInstitutionID}/{countryID}")
    public String editInstitution(HealthcareInstitution healthcareInstitution, RedirectAttributes redirectAttributes) {
        databaseController.editHealthcareInstitution(healthcareInstitution);

        redirectAttributes.addAttribute("healthcareInstitutionID", healthcareInstitution.getId());
        redirectAttributes.addAttribute("countryID", healthcareInstitution.getCountry().getId());
        return "redirect:/institution/{healthcareInstitutionID}/{countryID}";
    }

    @PostMapping("/role/edit/{roleName}/{healthcareInstitutionID}/{countryID}")
    public String editRole(Role role, RedirectAttributes redirectAttributes, @PathVariable String roleName) {
        role.setName(roleName);

        databaseController.editRole(role);

        redirectAttributes.addAttribute("roleName", role.getName());
        redirectAttributes.addAttribute("healthcareInstitutionID", role.getHealthcareInstitution().getId());
        redirectAttributes.addAttribute("countryID", role.getHealthcareInstitution().getCountry().getId());
        return "redirect:/role/{roleName}/{healthcareInstitutionID}/{countryID}";
    }

    @PostMapping("/employee/edit/{username}")
    public String editEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        databaseController.editEmployee(employee);

        redirectAttributes.addAttribute("username", employee.getUsername());
        return "redirect:/employee/{username}";
    }

    @PostMapping("/warning/edit/{id}")
    public String editWarning(Warning warning, RedirectAttributes redirectAttributes) {
        databaseController.editWarning(warning);

        redirectAttributes.addAttribute("id", warning.getId());
        return "redirect:/warning/{id}";
    }
}
