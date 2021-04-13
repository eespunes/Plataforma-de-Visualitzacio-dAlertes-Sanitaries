package tfg.eespunes.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tfg.eespunes.domain.HealthcareInstitution;

import javax.validation.Valid;

@Controller
public class WebPostController {


    public WebPostController() {
    }

    @PostMapping("/institution/create")
    public String createInstitution(@Valid HealthcareInstitution healthcareInstitution, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "healthcareInstitution/createHealthcareInstitution";
        }

//        model.addAttribute("username", user.getUsername());
//        userController.addUser(user);
        return "redirect:/";
    }
}
