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
public class WarningController {
    private DatabaseController databaseController;

    public WarningController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    //CREAR
    @GetMapping("/warning/create")
    public String createWarning(Warning warning, Model model) {
        model.addAttribute("roles", databaseController.getAllRoles());
        return "warning/createWarning";
    }

    @PostMapping("/warning/create")
    public String createWarningPOST(@Valid Warning warning, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return createWarning(warning, model);
        }

        String[] role = warning.getTempRoleName().split(" - ");
        warning.setRole(databaseController.getRole(role[2], Integer.parseInt(role[1]), role[0]));

        warning.setId(databaseController.addWarning(warning));

        redirectAttributes.addAttribute("id", warning.getId());
        return "redirect:/warning/{id}";
    }

    //GET ALL
    @GetMapping("/warning/all")
    public String getAllWarnings(Model model) {
        model.addAttribute("warnings", databaseController.getAllWarnings());
        return "warning/showWarnings";
    }

    //GET ONE
    @GetMapping("/warning/{id}")
    public String getWarning(@PathVariable int id, Model model) {
        model.addAttribute("warning", databaseController.getWarning(id));
        return "warning/showWarning";
    }


    //EDIT
    @GetMapping("/warning/edit/{id}")
    public String editWarning(@PathVariable int id, Model model) {
        model.addAttribute("warning", databaseController.getWarning(id));
        return "warning/editWarning";
    }

    @PostMapping("/warning/edit/{id}")
    public String editWarningPOST(Warning warning, RedirectAttributes redirectAttributes) {
        databaseController.editWarning(warning);

        redirectAttributes.addAttribute("id", warning.getId());
        return "redirect:/warning/{id}";
    }

    //DELETE
    @GetMapping("/warning/delete/{id}")
    public String deleteWarning(@PathVariable int id, Model model) {
        databaseController.deleteWarning(id);

        return getAllWarnings(model);
    }
}