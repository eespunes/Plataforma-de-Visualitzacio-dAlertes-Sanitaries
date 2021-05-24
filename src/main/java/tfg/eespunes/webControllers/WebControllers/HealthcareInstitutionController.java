package tfg.eespunes.webControllers.WebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class HealthcareInstitutionController {
    private final DatabaseController databaseController;

    public HealthcareInstitutionController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @GetMapping("/institution/create")
    public String createInstitutionGET(HealthcareInstitution healthcareInstitution,Model model) {
        model.addAttribute("countries", databaseController.getAllCountries());
        return "healthcareInstitution/createHealthcareInstitution";
    }

    @PostMapping("/institution/create")
    public String createInstitutionPOST(@Valid HealthcareInstitution healthcareInstitution, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return createInstitutionGET(healthcareInstitution,model);
        }

        healthcareInstitution.setCountry(databaseController.getCountry(healthcareInstitution.getTempCountry()));
        healthcareInstitution.setId(databaseController.addHealthcareInstitution(healthcareInstitution));

        redirectAttributes.addAttribute("healthcareInstitutionID", healthcareInstitution.getId());
        redirectAttributes.addAttribute("countryID", healthcareInstitution.getCountry().getId());
        return "redirect:/institution/{healthcareInstitutionID}/{countryID}";
    }

    //GET ALL
    @GetMapping("/institution/all")
    public String getAllInstitutions(Model model) {
        model.addAttribute("healthcareInstitutions", databaseController.getAllHealthcareInstitutions());
        return "healthcareInstitution/showHealthcareInstitutions";
    }

    //GET ONE
    @GetMapping("/institution/{healthcareInstitutionID}/{countryID}")
    public String getInstitution(@PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        model.addAttribute("healthcareInstitution", databaseController.getHealthcareInstitution(healthcareInstitutionID, countryID));
        model.addAttribute("roles", databaseController.getAllRolesOfHealthcareInstitution(healthcareInstitutionID, countryID));
        return "healthcareInstitution/showHealthcareInstitution";
    }

    //EDIT
    @GetMapping("/institution/edit/{healthcareInstitutionID}/{countryID}")
    public String editInstitution(@PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        model.addAttribute("healthcareInstitution", databaseController.getHealthcareInstitution(healthcareInstitutionID, countryID));
        return "healthcareInstitution/editHealthcareInstitution";
    }

    @PostMapping("/institution/edit/{healthcareInstitutionID}/{countryID}")
    public String editInstitutionPOST(HealthcareInstitution healthcareInstitution, RedirectAttributes redirectAttributes, @PathVariable int healthcareInstitutionID,@PathVariable String countryID) {
        healthcareInstitution.setCountry(databaseController.getCountry(countryID));
        healthcareInstitution.setId(healthcareInstitutionID);
        databaseController.editHealthcareInstitution(healthcareInstitution);

        redirectAttributes.addAttribute("healthcareInstitutionID", healthcareInstitution.getId());
        redirectAttributes.addAttribute("countryID", healthcareInstitution.getCountry().getId());
        return "redirect:/institution/{healthcareInstitutionID}/{countryID}";
    }


    //DELETE
    @GetMapping("/institution/delete/{healthcareInstitutionID}/{countryID}")
    public String deleteInstitution(@PathVariable int healthcareInstitutionID, @PathVariable String countryID, Model model) {
        databaseController.deleteHealthcareInstitution(healthcareInstitutionID, countryID);

        return getAllInstitutions(model);
    }
}