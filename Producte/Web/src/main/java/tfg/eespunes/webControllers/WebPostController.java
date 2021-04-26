package tfg.eespunes.webControllers;

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

    public WebPostController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @PostMapping("/institution/create")
    public String createInstitution(HealthcareInstitution healthcareInstitution, Model model, RedirectAttributes redirectAttributes) {
        databaseController.addHealthcareInstitution(healthcareInstitution);

        redirectAttributes.addAttribute("healthcareInstitutionID", healthcareInstitution.getId());
        redirectAttributes.addAttribute("countryID", healthcareInstitution.getCountryID());
        return "redirect:/institution/{healthcareInstitutionID}/{countryID}";
    }

    @PostMapping("/role/create")
    public String createRole(Role role, Model model, RedirectAttributes redirectAttributes) {
        role.setCountryIDFromHealthcareInstitution();
        databaseController.addRole(role);

        redirectAttributes.addAttribute("roleName", role.getName());
        redirectAttributes.addAttribute("healthcareInstitutionID",role.getHealthcareInstitutionID() );
        redirectAttributes.addAttribute("countryID", role.getCountryID());
        return "redirect:/role/{roleName}/{healthcareInstitutionID}/{countryID}";
    }

    @PostMapping("/employee/create")
    public String createUser(Employee employee, Model model, RedirectAttributes redirectAttributes) {
        employee.setCountryIDAndHealthcareInstitutionIDFromRoleName();
        databaseController.addEmployee(employee);

        redirectAttributes.addAttribute("id", employee.getId());
        return "redirect:/employee/{id}";
    }

    @PostMapping("/warning/create")
    public String createWarning(Warning warning, Model model, RedirectAttributes redirectAttributes) {
        warning.setCountryIDAndHealthcareInstitutionIDFromRoleName();
        databaseController.addWarning(warning);

        redirectAttributes.addAttribute("id", warning.getId());
        return "redirect:/warning/{id}";
    }
}
