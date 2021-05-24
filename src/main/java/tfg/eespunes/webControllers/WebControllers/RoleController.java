package tfg.eespunes.webControllers.WebControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class RoleController {
    private DatabaseController databaseController;

    public RoleController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //CREAR
    @GetMapping("/role/create")
    public String createRole(Role role, Model model) {
        model.addAttribute("healthcareInstitutions", databaseController.getAllHealthcareInstitutions());
        return "role/createRole";
    }

    @PostMapping("/role/create")
    public String createRolePOST(@Valid Role role, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return createRole(role, model);
        }

        String[] healthcareInstitution = role.getTempHealthcareInstitution().split(" - ");
        role.setHealthcareInstitution(databaseController.getHealthcareInstitution(Integer.parseInt(healthcareInstitution[1]), healthcareInstitution[0]));
        databaseController.addRole(role);

        redirectAttributes.addAttribute("roleName", role.getName());
        redirectAttributes.addAttribute("healthcareInstitutionID", role.getHealthcareInstitution().getId());
        redirectAttributes.addAttribute("countryID", role.getHealthcareInstitution().getCountry().getId());
        return "redirect:/role/{roleName}/{healthcareInstitutionID}/{countryID}";
    }

    //GET ALL
    @GetMapping("/role/all")
    public String getAllRoles(Model model) {
        model.addAttribute("roles", databaseController.getAllRoles());
        return "role/showRoles";
    }

    //GET ONE
    @GetMapping("/role/{roleName}/{healthcareInstitutionID}/{countryID}")
    public String getRole(@PathVariable String roleName, @PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        model.addAttribute("role", databaseController.getRole(roleName, healthcareInstitutionID, countryID));
        model.addAttribute("warnings", databaseController.getAllWarningsOfRole(roleName, healthcareInstitutionID, countryID));
        model.addAttribute("employees", databaseController.getAllEmployeesOfRole(roleName, healthcareInstitutionID, countryID));
        return "role/showRole";
    }

    //EDIT
    @GetMapping("/role/edit/{roleName}/{healthcareInstitutionID}/{countryID}")
    public String editRole(@PathVariable String roleName, @PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        model.addAttribute("role", databaseController.getRole(roleName, healthcareInstitutionID, countryID));
        model.addAttribute("warnings", databaseController.getAllWarningsOfRole(roleName, healthcareInstitutionID, countryID));
        return "role/editRole";
    }

    @PostMapping("/role/edit/{roleName}/{healthcareInstitutionID}/{countryID}")
    public String editRolePOST(Role role, @PathVariable String roleName, @PathVariable int healthcareInstitutionID, @PathVariable String countryID, RedirectAttributes redirectAttributes) {
        role.setName(roleName);
        role.setHealthcareInstitution(databaseController.getHealthcareInstitution(healthcareInstitutionID, countryID));
        databaseController.editRole(role);

        redirectAttributes.addAttribute("roleName", role.getName());
        redirectAttributes.addAttribute("healthcareInstitutionID", role.getHealthcareInstitution().getId());
        redirectAttributes.addAttribute("countryID", role.getHealthcareInstitution().getCountry().getId());
        return "redirect:/role/{roleName}/{healthcareInstitutionID}/{countryID}";
    }

    //DELETE
    @GetMapping("/role/delete/{roleName}/{healthcareInstitutionID}/{countryID}")
    public String deleteRole(@PathVariable String roleName, @PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        databaseController.deleteRole(roleName, healthcareInstitutionID, countryID);

        return getAllRoles(model);
    }
}