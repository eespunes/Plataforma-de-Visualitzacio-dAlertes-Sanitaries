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
    public String createInstitution(@Valid HealthcareInstitution healthcareInstitution, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "healthcareInstitution/createHealthcareInstitution";
        }

//        model.addAttribute("username", user.getUsername());
        databaseController.addHealthcareInstitution(healthcareInstitution);
        return "redirect:/role/createRole";
    }

    @PostMapping("/role/create")
    public String createRole(@Valid Role role, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "role/createRole";
        }
        role.setCountryIDFromHealthcareInstitution();
//        model.addAttribute("username", user.getUsername());
        databaseController.addRole(role);
        return "redirect:/";
    }

    @PostMapping("/user/create")
    public String createUser(@Valid Employee employee, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "employee/createEmployee";
        }
        employee.setCountryIDAndHealthcareInstitutionIDFromRoleName();
//        model.addAttribute("username", user.getUsername());
        databaseController.addEmployee(employee);
        return "redirect:/";
    }

    @PostMapping("/warning/create")
    public String createWarning(@Valid Warning warning, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "warning/createWarning";
        }
        warning.setCountryIDAndHealthcareInstitutionIDFromRoleName();
//        model.addAttribute("username", user.getUsername());
        databaseController.addWarning(warning);
        return "redirect:/";
    }
}
